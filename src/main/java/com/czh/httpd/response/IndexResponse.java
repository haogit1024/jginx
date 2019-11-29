package com.czh.httpd.response;

import com.czh.httpd.App;
import com.czh.httpd.util.ResourcesLoader;

import java.io.IOException;

/**
 * @author chenzh
 */
public class IndexResponse implements IResponse {
    @Override
    public String getHeader(int length) {
        String resHeader = """
                HTTP/1.1 404 OK
                Server:czh Server/0.0.1
                Date:Fri Nov 29 14:22:24 CST 2019
                Content-type:text/html;charset=GBK
                Content-Length:%d
                """;
        resHeader = String.format(resHeader, length);
        resHeader += App.CRLF;
        return resHeader;
    }

    @Override
    public String getContent() throws IOException {
        return ResourcesLoader.getResourceAsString(App.INDEX_PAGE);
    }

    @Override
    public String getResponse() throws IOException {
        String content = getContent();
        String header = getHeader(content.length());
        return header + content;
    }
}
