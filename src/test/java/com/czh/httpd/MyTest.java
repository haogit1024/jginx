package com.czh.httpd;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class MyTest {
    @Test
    public void testString() {
        final String CRLF="\r\n";
        final String BLANK=" ";
        StringBuilder str2=new StringBuilder();
        str2.append("HTTP/1.1").append(BLANK).append(404).append(BLANK);
        str2.append("OK");
        str2.append(CRLF);
        //2)  响应头(Response Head)
        str2.append("Server:bjsxt Server/0.0.1").append(CRLF);
        str2.append("Date:").append(new Date()).append(CRLF);
        str2.append("Content-type:text/html;charset=GBK").append(CRLF);
        //正文长度 ：字节长度
        str2.append("Content-Length:").append(100).append(CRLF);
        str2.append(CRLF);
        //将请求头请求体写出
        System.out.println(str2.toString());
    }

    @Test
    public void testHtml() {
        StringBuilder str1=new StringBuilder();
        str1.append("<html>");
        str1.append("<head>");
        str1.append("<title>respose</title>");
        str1.append("</head>");
        str1.append("<body>");
        str1.append("Hello World!");
        str1.append("</body>");
        str1.append("</html>");
        System.out.println(str1.toString());
    }
}
