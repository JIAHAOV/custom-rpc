package com.study.rpc.framework.loadBalance;

import com.study.rpc.framework.register.Instance;

import java.util.List;

/**
* 随机负载均衡算法
* @author: JiaHao
* @createTime: 2023/1/8 19:58
**/
public class RandomLoadBalance implements LoadBalance {
    @Override
    public Instance getInstance(List<Instance> list) {
        int index = (int) (Math.random() * list.size());
        return list.get(index);
    }
}
