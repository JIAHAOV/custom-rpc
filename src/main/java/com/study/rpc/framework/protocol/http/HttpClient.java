package com.study.rpc.framework.protocol.http;

import com.study.rpc.framework.Invocation;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
* Http 客户端，消费者据此使用 Http 发送调用信息。
* @author: JiaHao
* @createTime: 2023/1/5 20:11
**/
public class HttpClient {
    public Object send(String host, Integer port, Invocation invocation) {
        Object result = null;
        try {
            //创建连接
            URL url = new URL("http", host, port, "/demo");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            //发送调用信息
            OutputStream outputStream = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();
            //获取调用结果
            InputStream inputStream = connection.getInputStream();

            ObjectInputStream ois = new ObjectInputStream(inputStream);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
