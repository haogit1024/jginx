package com.czh.httpd.entity;

import lombok.Data;

import java.util.List;

/**
 * @author chenzh
 * @date 2020/10/9
 */
@Data
public class Server {
    // 监听端口
    private Integer listen;
    // 工作目录
    private String root;
    // 默认页配置
    private List<String> index;
    // 转发路由配置
    private List<Location> location;

    @Data
    public static class Location {
        private String url;
    }
}
