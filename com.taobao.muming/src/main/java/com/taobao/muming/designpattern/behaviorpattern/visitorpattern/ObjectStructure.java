package com.taobao.muming.designpattern.behaviorpattern.visitorpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class ObjectStructure {
    public static List<AbstractXxxElement> getList(){
        List<AbstractXxxElement> list = new ArrayList<AbstractXxxElement>();
        Random ran = new Random();
        for(int i=0; i<10; i++){
            int a = ran.nextInt(100);
            if(a>50){
                list.add(new AaXxxElement());
            }else{
                list.add(new BbXxxElement());
            }
        }
        return list;
    }
}
