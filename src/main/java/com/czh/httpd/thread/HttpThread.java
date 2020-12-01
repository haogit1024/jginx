package com.czh.httpd.thread;

import com.czh.httpd.entity.Server;
import com.czh.httpd.exception.HttpException;
import com.czh.httpd.handle.IRequestHandler;
import com.czh.httpd.handle.RequestHandlerFactory;
import com.czh.httpd.response.Response;
import com.czh.httpd.util.ArrayUtil;
import com.czh.httpd.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author czh
 * 处理http请求的线程, 每一个http请求生成一条线程
 */
public class HttpThread extends Thread {
    private final Socket socket;
    private final Server server;

    private final static Logger log = LogManager.getLogger(HttpThread.class);

    public HttpThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            // 打印每次请求的参数
            // TODO 作为静态服务器, 只获取请求头部分内容, 不获取请求体, 避免占用内存
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            int len = inputStream.read(bytes);
            InetAddress inetAddress = socket.getInetAddress();
            String requestData = new String(bytes, 0, len);
            log.info("-----request data begin-----");
            log.info(requestData);
            log.info("-----request data end-----");
            Response response;
            if (StringUtils.isNotBlank(requestData)) {
                try {
                    IRequestHandler requestHandler = RequestHandlerFactory.getNewHandler(requestData, server);
                    response = requestHandler.getResponse();
                } catch (HttpException e ) {
                    e.printStackTrace();
                    response = RequestHandlerFactory.getErrorHandler(requestData, e.getMessage()).getResponse();
                }
                OutputStream ost = socket.getOutputStream();
                byte[] res = ArrayUtil.mergeBytes(response.getResponseHeader().build().getBytes(), response.getResponseContent());
                ost.write(res);
            }
//            log.info("responseHeader: ");
//            log.info(response.getResponseHeader().build());
//            ost.write(response.getResponseHeader().build().getBytes());
//            ost.write(response.getResponseContent());
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("socket已关闭");
        }
    }
}
