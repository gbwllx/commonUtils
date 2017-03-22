package com.taobao.muming.dailytest.bytecode;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/28.
 */
public class BytecodeTest {
    public static String test$Impl() throws Exception{
        return "test";
    }

    public static String test() throws Exception{
        long start = System.currentTimeMillis();//统计开始时间
        Object result = test$Impl();//调用原方法
        long end = System.currentTimeMillis();//统计结束时间
        System.out.println("method test time used:" + (end - start));//计算时间
        return (String)result;
    }

    public static void modifyMethod(CtMethod method, CtClass clazz) throws Exception{
        System.out.println("muming");
        //从原方法复制产生一个新的方法
        CtMethod newMethod = CtNewMethod. copy(method, clazz, null);

        //重命名原方法
        String methodName = method.getLongName();
        String oldName = method.getName()+ "$Impl1";

        method.setName(oldName);
        StringBuilder body = new StringBuilder();
        body.append( "{long start = System.currentTimeMillis();" );

        //如果有返回值，则记录返回值，没有则不记录
        if(method.getReturnType()==CtClass. voidType){
            body.append( oldName+ "($$);");
        } else{
            body.append( "Object result = " +oldName+"($$);" );
        }
        body.append( " long end = System.currentTimeMillis();"
                + "System.out.println(\"" +methodName+ "\""+
                "\"time used:\"+" + "(end - start));" );

        //如果有返回值，则添加return 语句
        if(method.getReturnType()==CtClass. voidType){
            body.append( "}");
        } else{
            body.append( "return result;}" );
        }
        newMethod.setBody(body.toString());
        clazz.addMethod(newMethod);


    }


    public static void main(String[] args) throws Exception{
        // 实例化类型池对象
        ClassPool classPool = ClassPool.getDefault();

        CtClass ctClass = classPool.get("com.taobao.muming.dailytest.bytecode.BytecodeTest");

        try {
            //getMethod获取不到
            modifyMethod(ctClass.getDeclaredMethod("test"), ctClass);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
