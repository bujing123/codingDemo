package com.jd.ljy;

import com.jd.handler.*;
import org.junit.Test;

public class FansApplicationTest {

    /**
     * 第一步看到很多if....else就得想到使用策略模式，每一种逻辑抽离成一种策略
     */
    @Test
    String noDesign() {
        String key = "strategyA";
        if(key.equals("strategyA")){
            return "完成逻辑A";

        }else if(key.equals("strategyB")){
            System.out.println("进入业务逻辑");
        }

        return null;
    }


    @Test
    void design() {
        String key = "strategyA";
        AbstractHandler receiveHandler = StrategyFactory.getReceiveHandler(key);
        try{
            //业务逻辑A
            receiveHandler.logicA(key);
        }catch (UnsupportedOperationException e){
            // A策略不支持该方法
        }

        String s = null;
        try{
            //业务逻辑B
            s = receiveHandler.logicB(key);
        }catch (UnsupportedOperationException e){
            //B策略不支持该方法
        }
        System.out.println(s);

    }
}
