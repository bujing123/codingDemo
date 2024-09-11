package com.jd.handler;

import org.springframework.cglib.proxy.Factory;

public class StrategyAHandler extends AbstractHandler {
    /**
     * 业务逻辑A
     * @param key
     */
    @Override
    public void logicA(String key) {
        //业务逻辑A
        System.out.println("进入逻辑A");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyFactory.register("strategyA",this);
    }
}
