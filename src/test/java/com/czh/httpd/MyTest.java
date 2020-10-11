package com.czh.httpd;

import com.czh.httpd.constant.CommonConstants;
import com.czh.httpd.exception.ConfigException;
import com.czh.httpd.util.ResourcesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyTest {
    Logger log = LogManager.getLogger(MyTest.class);

    @Test
    public void testPrint() {
        System.out.println(File.separator);
    }

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

    @Test
    public void testDate() {
        System.out.println(new Date());
        log.error(new Date());
    }

    @Test
    public void testSplit() {
        String s = "a" + CommonConstants.Symbol.CRLF + "B" + CommonConstants.Symbol.CRLF + CommonConstants.Symbol.CRLF + CommonConstants.Symbol.CRLF + CommonConstants.Symbol.CRLF;
        String[] array = s.split(CommonConstants.Symbol.CRLF);
        System.out.println(array.length);
        for (String s1 : array) {
            System.out.println(s1);
        }
    }

    @Test
    public void testError() throws IOException {
        String errorHtml = ResourcesLoader.getResourceAsString("/static/error.html");
        System.out.println(String.format(errorHtml, "wdnmd"));
    }

    @Test
    public void testPath() {
        Path path = Paths.get(App.WORK_SPACE, "test.html");
        File file = path.toFile();
//        System.out.println(file.getName());
//        System.out.println(file.exists());
        String content = ResourcesLoader.getContent(file);
        System.out.println(content);
    }

    @Test
    public void testSpilt() {
        String name = "test.html";
        String[] array = name.split("\\.");
        for (String s : array) {
            System.out.println(s);
        }
    }

    @Test
    public void testContentType() {
        String filePath = "D:\\www\\test.png";
        File file = new File(filePath);
//        System.out.println(file.exists());
//        String type = new MimetypesFileTypeMap().getContentType(file);
//        System.out.println(type);
        long length = file.length();
        System.out.println("length: " + length);
        System.out.println(length / 1024 / 1024);
        System.out.printf("%d", 1L);
    }

    @Test
    public void testEnum() {
//        HttpStatus httpStatus = HttpStatus.valueOf(800);
//        System.out.println(httpStatus == null);
//        System.out.println(httpStatus.value());
//        System.out.println(httpStatus.getReasonPhrase());

        File file = new File("D:\\tomcat\\apache-tomcat-9.0.19\\webapps\\test\\test.png");
//        if (!file.exists()) {
//            return getNotFoundResponse(cookie, url);
//        }
        // 先粗暴处理
//        String contentType = new MimetypesFileTypeMap().getContentType(file);

//        System.out.println(contentType);
    }

    @Test
    public void testResourceLoad() throws IOException {
        String string = ResourcesLoader.getResourceAsString("fuck.html");
        System.out.println(string);
    }

    @Test
    public void testThreadPool() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executor.submit(() -> System.out.println("fuck you " + index));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    @Test
    public void testException() {
        try {
            throw new ConfigException("hello");
        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	System.out.println("hello eclipse");
    }
}
