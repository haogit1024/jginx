package com.czh.httpd.thread;

import com.czh.httpd.handle.IRequestHandle;
import com.czh.httpd.handle.SimpleRequestHandle;
import com.czh.httpd.response.Response;
import com.czh.httpd.response.ResponseFactory;
import com.czh.httpd.util.ArrayUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;

/**
 * @author chenzh
 * 处理http请求的线程, 每一个http请求生成一条线程
 */
public class HttpThread extends Thread {
    private Socket socket;

    public HttpThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
//        IndexResponse indexResponse = new IndexResponse();
        try {
            // 打印每次请求的参数
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            int len = inputStream.read(bytes);
            String requestData = new String(bytes, 0, len);
            Response response;
            if (StringUtils.isNotBlank(requestData)) {
                IRequestHandle requestHandle = new SimpleRequestHandle();
                requestHandle.setRequest(requestData);
                response = requestHandle.getResponse();
            } else {
                // 空请求头处理
                response = ResponseFactory.getIndexResponse("");
            }
            OutputStream ost = socket.getOutputStream();
            System.out.println("responseHeader: ");
            System.out.println(response.getResponseHeader().build());
//            ost.write(response.getResponseHeader().build().getBytes());
//            ost.write(response.getResponseContent());
            byte[] res = ArrayUtil.mergeBytes(response.getResponseHeader().build().getBytes(), response.getResponseContent());
            ost.write(res);
            socket.shutdownOutput();
            socket.getInputStream();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("socket已关闭");
        }
    }
}
