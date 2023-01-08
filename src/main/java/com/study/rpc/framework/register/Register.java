package com.study.rpc.framework.register;

import java.util.List;

/**
* 注册中心，注册生产者的实例信息
* @author: JiaHao
* @createTime: 2023/1/5 20:06
**/
public interface Register {
    /**
     * 通过服务名获取实例信息
     * @param serviceName 服务名
     * @return 实例的集合
     */
    List<Instance> getByName(String serviceName);

    /**
     * 注册实例信息
     * @param serviceName 服务名
     * @param instance 实例信息
     */
    void register(String serviceName, Instance instance);

    /**
     * 移除实例信息
     * @param serviceName 服务名
     * @param instance 实例信息
     */
    void remove(String serviceName, Instance instance);
}
