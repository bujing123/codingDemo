package com.jd.ljy;

import com.jd.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class designPatterns {
    @Test
    public void io() {
        IntStream
                .range(0, 10)
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);
    }

    @Test
    public void list01(){
        ArrayList<Father> fathers = new ArrayList<>();
        fathers.add(new Father("1",2,"1"));
        fathers.add(new Father("2",2,"1"));
        fathers.add(new Father("3",2,"1"));
        fathers.add(new Father("4",2,"1"));
        System.out.println(JSON.toJSONString(fathers));
        for (Father father : fathers) {
            father.setAge(8);
        }
        System.out.println(JSON.toJSONString(fathers));
    }


}
