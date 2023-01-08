package com.study.rpc.framework.protocol.http;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
* Http 服务端，生产者据此接受消费者传来的调用信息
* @author: JiaHao
* @createTime: 2023/1/5 20:13
**/
public class HttpServer {

    /**
     * 使用 tomcat 搭建服务器
     * @param port 端口号
     */
    public void start(Integer port) {
        Tomcat tomcatServer = new Tomcat();
        //设置tomcat端口
        tomcatServer.setPort(port);

        //此tomcat端口是否自动部署
        tomcatServer.getHost().setAutoDeploy(false);
        //创建一个web应用程序
        StandardContext standardContext = new StandardContext();
        //设置web应用程序的上下文地址
        standardContext.setPath("/demo");
        //添加web应用程序的监听
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        //将web应用程序添加到服务器中
        tomcatServer.getHost().addChild(standardContext);
        //配置servlet和映射
        tomcatServer.addServlet("/demo", "dispatchServlet", new DispatchServlet());
        standardContext.addServletMappingDecoded("/*", "dispatchServlet");
        //启动tomcat
        try {
            tomcatServer.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        //开启异步服务，接收请求
        tomcatServer.getServer().await();
    }
}
