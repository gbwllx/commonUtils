package com.taobao.muming.scxml.helloworld;

import org.apache.commons.scxml2.SCXMLExecutor;
import org.apache.commons.scxml2.env.groovy.GroovyEvaluator;
import org.apache.commons.scxml2.io.SCXMLReader;
import org.apache.commons.scxml2.model.ModelException;
import org.apache.commons.scxml2.model.SCXML;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URL;

/**
 * @description:
 * @description:
 * @author: gubing.gb
 * @date: 2017/1/17.
 */
public class HelloWorld {
    //通过加载HelloWorld类的类加载器加载helloworld.xml资源文件，得到URL
    private static final URL DEF_SOURCE = HelloWorld.class.getClassLoader().getResource("scxml/helloworld.xml");

    public static void main(String[] args) throws ModelException, XMLStreamException, IOException {
        //得到xml文件所对应的 SCXML对象
        SCXML scxml = SCXMLReader.read(DEF_SOURCE);
        //实例化状态机引擎
        SCXMLExecutor executor = new SCXMLExecutor();
        //将得到的SCXML对象，交给状态机引擎管理
        executor.setStateMachine(scxml);
        //设置evaluator
        executor.setEvaluator(new GroovyEvaluator());
        //然后引擎调用.go()方法启动状态机。
        executor.go();
    }
}
