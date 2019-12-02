package com.czh.httpd.header;

import com.czh.httpd.App;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class SimpleRequestHeaderTest {

    @Test
    void build() {
//        String header = """
//                Host: static.zhihu.com
//                User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0
//                Accept: image/webp,*/*
//                Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
//                Accept-Encoding: gzip, deflate, br
//                Connection: keep-alive
//                Cookie: _zap=f33b763b-fa07-409b-81a5-a6bbda85a9bc; _xsrf=IyiAEL7QNr8l0j9CSagIlHlGhYbUDQfy; d_c0="AGAiQNsh6A-PTvCmU3NegHmhmU3_lF6SX7A=|1566056169"; z_c0=Mi4xRUxaREFnQUFBQUFBWUNKQTJ5SG9EeGNBQUFCaEFsVk4tR3hGWGdES1JuX2V3VHhDWEV6N2VSYmFVVVNLOV9FbnR3|1566056184|2669df94896fe5e52f5f74aadf36b365c5399d15; tst=f; q_c1=ac028804f87e4da189245e4b5d28f7b3|1573737552000|1567303764000; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1572666940,1572787273,1573737459,1575072877; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1575073069
//                TE: Trailers
//                """;
        String header = "";
        SimpleRequestHeader simpleRequestHeader = new SimpleRequestHeader();
        String[] headerArray = header.split("\n");
        System.out.println(headerArray.length);
        for (String headerItem : headerArray) {
            if (StringUtils.isNotBlank(headerItem)) {
                String[] itemArray = headerItem.split(App.HEADER_SEPARATOR);
                simpleRequestHeader.setHeader(itemArray[0], itemArray[1]);
            }
        }
        System.out.println(simpleRequestHeader.build());
    }
}