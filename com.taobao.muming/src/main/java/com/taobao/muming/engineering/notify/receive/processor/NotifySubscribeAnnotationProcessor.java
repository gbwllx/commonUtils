package com.taobao.muming.engineering.notify.receive.processor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.taobao.muming.engineering.notify.base.Binding;
import com.taobao.muming.engineering.notify.receive.NotifySubscribeInfo;
import com.taobao.muming.engineering.notify.receive.NotifySubscribeType;
import com.taobao.muming.engineering.notify.base.MessageType;
import com.taobao.muming.engineering.notify.base.NotifyManagerBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 消息注解的处理器,用于订阅消息,需要配置在spring Context中
 * Created by bolang on 16/6/6.
 */
public class NotifySubscribeAnnotationProcessor implements ApplicationContextAware {
    // 匹配格式为 #{groupId}
    private static final String PATTERN = "^#\\{\\w+\\}$";

    // 扫描到的 callback bean
    private final List<NotifyMessageListenerCallback> notifyMessageListenerCallbacks = Lists.newCopyOnWriteArrayList();
    // groupId notifyManagerBean map
    private final Map<String/*groupId*/, NotifyManagerBean> notifyManagerBeanMap = Maps.newConcurrentMap();
    // groupId listener map  , 现在我们的 groupId notifyManagerBean listener都是一一对应的
    private final Map<String/*groupId*/, NotifyMessageListener> notifyMessageListenerMap = Maps.newConcurrentMap();
    // 是否开启debug
    private boolean enableDebug = false;
    // debug port , 开启 debug 有效
    private int debugPort;
    // placeholderMap
    private Map<String, String> placeholderMap = Maps.newHashMap();
    private final Pattern pattern = Pattern.compile(PATTERN);
    // 订阅生效配制
    private String subscribeEnableBean;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //LOGGER.info("NotifySubscribeAnnotationProcessor : 开始解析注解注册消息订阅!");
        this.applicationContext = applicationContext;

        // 获得所有带有 NotifySubscribeInfo 注解的 bean
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(NotifySubscribeInfo.class);
        if (beanMap != null && beanMap.size() > 0) {
            beanMap.entrySet().stream().forEach(beanEntry -> {
                // 过滤出所有的是 消息回调的 bean
                if (beanEntry.getValue() instanceof NotifyMessageListenerCallback) {
                    notifyMessageListenerCallbacks.add((NotifyMessageListenerCallback) beanEntry.getValue());
                }
            });
        }

        // 订阅
        this.doSubscribe();
    }

    /**
     * 订阅消息
     * 1. 按groupId分组
     * 2. 每个group执行 初始化消息管理bean 和 消息注册
     */
    private void doSubscribe() {
        // 按groupId分组
        Map<String, Set<NotifyMessageListenerCallback>> groupSubscribeInfoMap = this.groupSubscribeInfoMap();
        // 循环开始订阅消息
        groupSubscribeInfoMap.entrySet().stream().forEach(subscribeInfoEntry -> {
            // 初始化消息中心
            this.initNotifyManagerBean(subscribeInfoEntry.getKey());
            NotifyManagerBean notifyManagerBean = this.notifyManagerBeanMap.get(subscribeInfoEntry.getKey());
            // 添加订阅绑定
            final List<Binding> bindings = this.buildBindings(subscribeInfoEntry.getKey(), subscribeInfoEntry.getValue());
            // 注册订阅消息
            if (this.subscribeEnable(subscribeInfoEntry.getKey())) {
                notifyManagerBean.subscriberAfterInited(bindings);
            } else {
                notifyManagerBean.subscribe(bindings);
            }
        });
    }

    /**
     * 判断groupId是否要订阅
     *
     * @param groupId
     * @return
     */
    private boolean subscribeEnable(String groupId) {
        if (null != subscribeEnableBean) {
            SubscribeEnable subscribeEnable = this.applicationContext.getBean(subscribeEnableBean, SubscribeEnable.class);
            if (null != subscribeEnable) {
                return subscribeEnable.enable(groupId);
            }
        }

        return true;
    }

    /**
     * 将订阅信息按groupId分组
     *
     * @return
     */
    private Map<String, Set<NotifyMessageListenerCallback>> groupSubscribeInfoMap() {
        Map<String, Set<NotifyMessageListenerCallback>> groupSubscribeInfoMap = Maps.newHashMap();
        this.notifyMessageListenerCallbacks.stream().forEach(callback -> {
            // 获取 groupId
            String groupIdString = callback.getClass().getAnnotation(NotifySubscribeInfo.class).subscribeGroupId();
            String groupId;
            // 如果符合匹配规则,则从map中获取值
            groupId = this.ifReplace(groupIdString);

            if (groupSubscribeInfoMap.get(groupId) == null) {
                groupSubscribeInfoMap.put(groupId, Sets.newHashSet());
            }
            groupSubscribeInfoMap.get(groupId).add(callback);
        });
        return groupSubscribeInfoMap;
    }

    /**
     * 如果满足替换规则，则替换
     *
     * @param placeholderPatten
     * @return
     */
    private String ifReplace(String placeholderPatten) {
        if (StringUtils.isBlank(placeholderPatten)) {
            return placeholderPatten;
        }

        if (pattern.matcher(placeholderPatten).matches()) {
            String resultStr = placeholderMap.get(placeholderPatten.substring(2, placeholderPatten.length() - 1));
            if (StringUtils.isBlank(resultStr)) {
                throw new RuntimeException("未能从Map中取到该变量值, placeholderPatten:" + placeholderPatten);
            }
            return resultStr;
        } else {
            return placeholderPatten;
        }
    }


    /**
     * 初始化消息中心bean
     */
    private void initNotifyManagerBean(String groupId) {
        // 为空则生成一个,不为空说明已经通过Spring注入
        if (this.notifyManagerBeanMap.get(groupId) == null) {
            // 消息中心
            NotifyManagerBean notifyManagerBean = new NotifyManagerBean();
            notifyManagerBean.setGroupId(groupId);

            NotifyMessageListener listener = new NotifyMessageListener(groupId);
            this.notifyMessageListenerMap.put(groupId, listener);
            notifyManagerBean.setMessageListener(listener);//注册回调

            //TODO
            notifyManagerBean.setWaitForConnTime(50000);
            notifyManagerBean.setMessageTPCorePoolSize(30);
            notifyManagerBean.setMessageTPMaxPoolSize(50);

            // debug
            if (enableDebug) {
                notifyManagerBean.setDebug(true);
                notifyManagerBean.setDebugLocalPort(debugPort);
            }

            //TODO
            notifyManagerBean.init();
            this.notifyManagerBeanMap.put(groupId, notifyManagerBean);
        }
    }

    /**
     * 构建notify消息绑定
     */
    private List<Binding> buildBindings(String groupId, Set<NotifyMessageListenerCallback> callbacks) {
        //给配置的每个callback
        List<Binding> bindings = Lists.newArrayList();

        for (final NotifyMessageListenerCallback callback : callbacks) {
            // step1. 拿到具体callBack的信息
            NotifySubscribeInfo annotation = callback.getClass().getAnnotation(NotifySubscribeInfo.class);
            final MessageType[] messageTypes = annotation.messageType();
            String subscribeExp = annotation.subscribeExp();

            // 给每个messageType加监听，通常只有一个
            for (final MessageType messageType : messageTypes) {
                //step2.callback 放到receiver中 ，设置订阅方式
                this.buildBinding(groupId, callback, this.ifReplace(messageType.getTopic()), this.ifReplace(messageType.getType()), annotation.subscribeType(), subscribeExp, bindings);
            }
        }
        return bindings;
    }

    /**
     * 订阅消息
     *
     * @param callBack
     * @param topic
     * @param messageType
     * @param notifySubscribeType
     * @param subscribeExp
     */
    private void buildBinding(String groupId, NotifyMessageListenerCallback callBack, String topic, String messageType, NotifySubscribeType notifySubscribeType, String subscribeExp, List<Binding> bindings) {

        Preconditions.checkArgument(callBack != null && StringUtils.isNotBlank(topic) && notifySubscribeType != null,
                "消息注册参数错误!");

//        LOGGER.info(String.format("subscribe message, groupId:%s, callbackName:%s, topic:%s, messageType:%s, notifySubscribeType:%s, subscribeExp:%s",
//                groupId, callBack.getClass().getTypeName(), topic, messageType, notifySubscribeType.name(), subscribeExp));

        NotifyMessageListener listener = this.notifyMessageListenerMap.get(groupId);
        listener.addListenerCallback(callBack);

        Binding binding;
        switch (notifySubscribeType) {
            case DIRECT:
                binding = Binding.direct(topic, messageType, groupId, -1, true);
                break;
            case PATTERN:
                binding = Binding.pattern(topic, subscribeExp, groupId, -1, true);//正则表达式,暂时用 messageType
                break;
            case HEADER:
                binding = Binding.header(topic, subscribeExp, groupId, -1, true);//表达式
                break;
            case FANOUT:
                binding = Binding.fanout(topic, groupId, -1, true);
                break;
            default:
                binding = Binding.direct(topic, messageType, groupId, -1, true);
        }
        bindings.add(binding);
    }

    public boolean isEnableDebug() {
        return enableDebug;
    }

    public void setEnableDebug(boolean enableDebug) {
        this.enableDebug = enableDebug;
    }

    public int getDebugPort() {
        return debugPort;
    }

    public void setDebugPort(int debugPort) {
        this.debugPort = debugPort;
    }

    public Map<String, String> getPlaceholderMap() {
        return placeholderMap;
    }

    public void setPlaceholderMap(Map<String, String> placeholderMap) {
        this.placeholderMap = placeholderMap;
    }

    public void setSubscribeEnableBean(String subscribeEnableBean) {
        this.subscribeEnableBean = subscribeEnableBean;
    }
}
