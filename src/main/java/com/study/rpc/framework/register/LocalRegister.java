package com.study.rpc.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
* 生产者的本地注册中心，用于生产者的本地注册，生产者将提供服务进行注册，再通过调用信息找到对应的服务。
* @author: JiaHao
* @createTime: 2023/1/5 20:04
**/
public class LocalRegister {
    private static final Map<String, Class<?>> MAPPING = new HashMap<>();

    public static void register(String serviceName, Class<?> serviceClass) {
        MAPPING.put(serviceName, serviceClass);
    }

    public static Class<?> getByName(String serviceName) {
        return MAPPING.get(serviceName);
    }

    public static void remove(String serviceName) {
        MAPPING.remove(serviceName);
    }
}
