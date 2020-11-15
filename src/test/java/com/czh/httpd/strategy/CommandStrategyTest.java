package com.czh.httpd.strategy;

import com.czh.httpd.constant.CommonConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandStrategyTest {

    @Test
    void stop() {
        CommandStrategy.run("stop");
        System.out.println(CommonConstants.Symbol.CRLF.length());
    }

    @Test
    void restart() {
        CommandStrategy.run("restart");
    }
}