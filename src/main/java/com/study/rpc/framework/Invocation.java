package com.study.rpc.framework;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
* 用于传输调用详情的实体，被调用者由此了解调用者调用的是哪个方法
* @author: JiaHao
* @createTime: 2023/1/5 19:53
**/
@Data
@Builder
public class Invocation implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 调用的类名
     */
    private String serviceName;
    /**
     * 调用的方法名
     */
    private String methodName;
    /**
     * 方法的参数类型
     */
    private Class<?>[] parameterTypes;
    /**
     * 方法的参数
     */
    private Object[] parameters;
}
