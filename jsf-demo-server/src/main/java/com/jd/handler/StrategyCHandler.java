package com.jd.handler;

public class StrategyCHandler implements Handler{
    @Override
    public void logic(String key) {
        //业务逻辑C
        System.out.println("进入逻辑C");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
