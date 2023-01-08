package com.study.rpc.consumer;

import com.study.rpc.framework.ProxyFactory;
import com.study.rpc.common.api.UserService;

public class Consumer {
    public static void main(String[] args) {
        UserService userService = ProxyFactory.getProxy(UserService.class);
        //消费之通过实体直接调用方法
        String rose = userService.sayHello("rose");
        System.out.println(rose);
        Integer random = userService.random();
        System.out.println(random);
    }
}
