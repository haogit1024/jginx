package com.czh.httpd;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author chenzh
 */
public class App {
    /**
     * http换行符
     */
    public static final String CRLF="\r\n";
    /**
     * 空格
     */
    public static final String BLANK=" ";

    /**
     * 是否可以运行
     */
    public static boolean RUN_ABLE = true;

    public static final String INDEX_PAGE = "/static/index.html";
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8099);
            while (RUN_ABLE) {
                Socket socket = server.accept();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            ServerSocket server = new ServerSocket(8099);
            Socket socket = server.accept();
            System.out.println("socket接受到了请求");
            InputStream inputStream = socket.getInputStream();
            System.out.println("开始解释请求数据");
            int length = inputStream.available();
            // 一次解析1Mb
            byte[] bytes = new byte[length];
            int readLength = inputStream.read(bytes);
            System.out.printf("length: %d, readLength: %d \n", length, readLength);
            String inputData = new String(bytes, 0, readLength);
            System.out.println("接收到的数据");
            System.out.println(inputData);
            System.out.println();
            System.out.println("-------");
            //响应流
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //请求体
            StringBuilder str1=new StringBuilder();
            str1.append("<html>");
            str1.append("<head>");
            str1.append("<title>respose</title>");
            str1.append("</head>");
            str1.append("<body>");
            str1.append("Hello World!");
            str1.append("</body>");
            str1.append("</html>");
            //请求头
            StringBuilder str2=new StringBuilder();
            str2.append("HTTP/1.1").append(BLANK).append(404).append(BLANK);
            str2.append("OK");
            str2.append(CRLF);
            //2)  响应头(Response Head)
            str2.append("Server:bjsxt Server/0.0.1").append(CRLF);
            str2.append("Date:").append(new Date()).append(CRLF);
            str2.append("Content-type:text/html;charset=GBK").append(CRLF);
            //正文长度 ：字节长度
            str2.append("Content-Length:").append(length).append(CRLF);
            str2.append(CRLF);
            //将请求头请求体写出
            bw.append(str2.toString());
            bw.append(str1.toString());
            bw.flush();
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务启动失败");
        }*/

    }
}
