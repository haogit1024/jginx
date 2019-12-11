package com.czh.httpd.thread;

import com.czh.httpd.handle.IRequestHandle;
import com.czh.httpd.handle.SimpleRequestHandle;
import com.czh.httpd.response.Response;
import com.czh.httpd.util.ArrayUtil;
import com.czh.httpd.util.StringUtils;

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
            System.out.println(requestData);
            System.out.println("----------");
            Response response;
            if (StringUtils.isNotBlank(requestData)) {
                IRequestHandle requestHandle = new SimpleRequestHandle();
                requestHandle.setRequest(requestData);
                response = requestHandle.getResponse();
                OutputStream ost = socket.getOutputStream();
                byte[] res = ArrayUtil.mergeBytes(response.getResponseHeader().build().getBytes(), response.getResponseContent());
                ost.write(res);

            }
//            System.out.println("responseHeader: ");
//            System.out.println(response.getResponseHeader().build());
//            ost.write(response.getResponseHeader().build().getBytes());
//            ost.write(response.getResponseContent());
            socket.shutdownOutput();
            socket.getInputStream();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("socket已关闭");
        }
    }
}
