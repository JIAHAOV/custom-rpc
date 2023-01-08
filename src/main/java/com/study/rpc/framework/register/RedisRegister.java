package com.study.rpc.framework.register;

import cn.hutool.json.JSONUtil;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* 通过 Redis 进行实例的注册，可采用心跳机制，此时仅是简单的实现注册功能
* @author: JiaHao
* @createTime: 2023/1/5 20:06
**/
public class RedisRegister implements Register{

    private final Jedis jedis;

    private static final String REGISTER_PREFIX = "dubbo-demo:";

    public RedisRegister() {
        //此处可进行配置的自定义

        //1、简历连接
        jedis =  new Jedis("127.0.0.1",6379);
        //2、密码
        jedis.auth("password");
        //3、选择库
        jedis.select(0);
    }

    @Override
    public List<Instance> getByName(String serviceName) {
        String key = REGISTER_PREFIX + serviceName;
        Set<String> strings = jedis.smembers(key);
        return strings.stream().map(s -> JSONUtil.toBean(s, Instance.class)).collect(Collectors.toList());
    }

    @Override
    public void register(String serviceName, Instance instance) {
        String key = REGISTER_PREFIX + serviceName;
        jedis.sadd(key, JSONUtil.toJsonStr(instance));
    }

    @Override
    public void remove(String serviceName, Instance instance) {
        String key = REGISTER_PREFIX + serviceName;
        jedis.srem(key, JSONUtil.toJsonStr(instance));
    }
}
