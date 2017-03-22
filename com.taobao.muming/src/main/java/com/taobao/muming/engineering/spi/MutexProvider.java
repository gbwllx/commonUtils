package com.taobao.muming.engineering.spi;

import java.util.Iterator;
import java.util.List;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public interface MutexProvider<T, R> extends SpiNodesRegister<T, R> {
    //Logger LOG = LoggerFactory.getLogger(MutexProvider.class);

    /**
     * 互斥执行业务识别函数
     *
     * @param input
     * @return
     * @throws Exception
     */
    default R execute(T input) throws Exception {
        List<SpiNode<T, R>> spis = this.getSpiNodes();
        for (Iterator<SpiNode<T, R>> iterator = spis.iterator(); iterator.hasNext(); ) {
            SpiNode<T, R> spi = iterator.next();
            if (spi.recognize(input)) {
                //LOG.info("MutexProvider.spi:" + spi.getClass());
                return spi.apply(input);
            }
        }

        return null;
    }
}
