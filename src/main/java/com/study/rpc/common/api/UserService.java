package com.study.rpc.common.api;

/**
* 接口规范，使生产者和消费者统一规范
* @author: JiaHao
* @createTime: 2023/1/5 20:18
**/
public interface UserService {

    String sayHello(String name);

    Integer random();
}
