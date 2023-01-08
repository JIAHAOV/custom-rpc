package com.study.rpc.framework.protocol.http;

import com.study.rpc.framework.Invocation;
import com.study.rpc.framework.register.LocalRegister;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
* 生产者处理接受到的调用信息
* @author: JiaHao
* @createTime: 2023/1/5 20:14
**/
public class HttpServerHandler {
    void doHandler(ServletRequest servletRequest, ServletResponse servletResponse) {
        try {
            //读取对象
            ServletInputStream inputStream = servletRequest.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) ois.readObject();
            //通过名称获取服务并反射调用
             //通过服务名获取服务类
            String serviceName = invocation.getServiceName();
            Class<?> clazz = LocalRegister.getByName(serviceName);
             //通过方法名获取方法
            String methodName = invocation.getMethodName();
            Class<?>[] parameterTypes = invocation.getParameterTypes();
            Method method = clazz.getMethod(methodName, parameterTypes);
             //调用方法
            Object service = clazz.getConstructor().newInstance();
            Object[] parameters = invocation.getParameters();
            Object result = method.invoke(service, parameters);
            //返回调用结果
            ServletOutputStream outputStream = servletResponse.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(result);
            oos.flush();
            oos.close();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
