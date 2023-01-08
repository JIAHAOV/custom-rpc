package com.study.rpc.framework.protocol.http;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
* 接受调用信息的 Servlet
* @author: JiaHao
* @createTime: 2023/1/5 20:16
**/
public class DispatchServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //可在此处自定义处理器
        HttpServerHandler handler = new HttpServerHandler();
        handler.doHandler(servletRequest, servletResponse);
    }
}
