package com.study.rpc.framework.loadBalance;

import com.study.rpc.framework.register.Instance;

import java.util.List;

/**
* 负载均衡策略，当有多个实例时，由此确定调用哪个实例
* @author: JiaHao
* @createTime: 2023/1/5 19:56
**/
public interface LoadBalance {
    Instance getInstance(List<Instance> list);
}
