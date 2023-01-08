package com.study.rpc.provider;

import com.study.rpc.framework.protocol.http.HttpServer;
import com.study.rpc.framework.register.Instance;
import com.study.rpc.framework.register.LocalRegister;
import com.study.rpc.framework.register.RedisRegister;
import com.study.rpc.common.api.UserService;
import com.study.rpc.provider.service.UserServiceImpl;

public class Provider {
    public static void main(String[] args) {
        //服务进行本地注册
        LocalRegister.register(UserService.class.getSimpleName(), UserServiceImpl.class);
        Instance instance = new Instance();
        instance.setHost("127.0.0.1");
        instance.setPort(8080);
        //将实例信息注册进注册中心
        new RedisRegister().register(UserService.class.getSimpleName(), instance);
        //启动服务器，接受调用信息
        new HttpServer().start(8080);
    }
}
