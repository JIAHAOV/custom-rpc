package com.study.rpc.framework.register;

import lombok.Data;

/**
* 实例对象的实体类
* @author: JiaHao
* @createTime: 2023/1/5 20:03
**/
@Data
public class Instance {
    /**
     * 实例的地址
     */
    private String host;
    /**
     * 实例的端口
     */
    private Integer port;
}
