package com.study.rpc.provider.service;

import com.study.rpc.common.api.UserService;

/**
* 服务实现类
* @author: JiaHao
* @createTime: 2023/1/5 20:19
**/
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "hello: " + name;
    }

    @Override
    public Integer random() {
        return (int) (Math.random() * 1000);
    }
}
