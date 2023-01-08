package com.study.rpc.framework;

import com.study.rpc.framework.loadBalance.RandomLoadBalance;
import com.study.rpc.framework.protocol.http.HttpClient;
import com.study.rpc.framework.register.Instance;
import com.study.rpc.framework.register.RedisRegister;

import java.lang.reflect.Proxy;
import java.util.List;

/**
* 代理工厂，代理消费者对应调用的接口方法
* @author: JiaHao
* @createTime: 2023/1/5 19:59
**/
public class ProxyFactory {

    @SuppressWarnings({"unchecked"})
    public static <T> T getProxy(Class<T> clazz) {
        Object proxyInstance = Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class[]{clazz}, (proxy, method, args) -> {
            //可以通过任意方式发送调用信息，此处采用 Http 请求的方法。
            HttpClient httpClient = new HttpClient();
            //封装消费者的调用信息
            Invocation invocation = Invocation.builder()
                    .serviceName(clazz.getSimpleName())
                    .methodName(method.getName())
                    .parameterTypes(method.getParameterTypes())
                    .parameters(args)
                    .build();
            //通过注册中心获取生产实例的地址，此处采用 redis 作为注册中心。
            RedisRegister register = new RedisRegister();
            List<Instance> instances = register.getByName(clazz.getSimpleName());
            //通过负载均衡算法，获取实例地址
            Instance instance = new RandomLoadBalance().getInstance(instances);
            System.out.println("调用" + instance.getPort());
            //将调用信息发送给生产者并将调用结果返回
            return httpClient.send(instance.getHost(), instance.getPort(), invocation);
        });

        return (T) proxyInstance;
    }
}
