package com.czh.httpd.entity;

import lombok.Data;

import java.util.List;

/**
 * @author chenzh
 * @date 2020/10/9
 */
@Data
public class Server {
    private Integer listen;
    private List<Location> location;

    @Data
    public static class Location {
        private String url;
    }
}
