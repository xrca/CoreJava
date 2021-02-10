package com.saury.jcf;

import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

public class HashMapDemo {

    @Test
    public void test() {
        HashMap<String, String> map = new HashMap<>();

        map.put("saury", "hahaha");
        //map.put("saury", "hihihi");
        map.putIfAbsent("saury", "hihihi");

        System.out.println(map.get("saury"));
        System.out.println(Math.pow(2, 31));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1 << 30);

        System.out.println(map.remove("saury", "1"));

        System.out.println(8 >>> 2);
    }
}
