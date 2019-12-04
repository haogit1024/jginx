package com.czh.httpd.thread;

import com.czh.httpd.handle.IRequestHandle;
import com.czh.httpd.handle.SimpleRequestHandle;
import com.czh.httpd.response.Response;
import com.czh.httpd.response.ResponseFactory;
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
            String response;
            if (StringUtils.isNotBlank(requestData)) {
                IRequestHandle requestHandle = new SimpleRequestHandle();
                requestHandle.setRequest(requestData);
                response = requestHandle.getResponse();
            } else {
                // 空请求头处理
                Response indexResponse = ResponseFactory.getIndexResponse("");
                response = indexResponse.build();
            }
            OutputStream ost = socket.getOutputStream();
            // TODO 改位写入byte[] 方法
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ost));
            bw.write(response);
            bw.flush();
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("socket已关闭");
        }
    }
}
