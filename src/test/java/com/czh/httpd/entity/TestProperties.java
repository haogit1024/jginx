package com.czh.httpd.entity;

public class TestProperties {
    private Integer port;
    private Integer number;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "TestProperties{" +
                "port=" + port +
                ", number=" + number +
                '}';
    }
}
