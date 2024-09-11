package com.jd.handler;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {
    private static Map<String, AbstractHandler> strategyMap = new HashMap<>();

    public static AbstractHandler getReceiveHandler(String methodName){
        return strategyMap.get(methodName);
    }

    public static void register(String name, AbstractHandler handler){
        if(StringUtils.isBlank(name) || null == handler){
            return;
        }
        strategyMap.put(name,handler);
    }
}
