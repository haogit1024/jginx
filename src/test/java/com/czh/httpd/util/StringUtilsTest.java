package com.czh.httpd.util;

import com.czh.httpd.constant.CommonConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void removePrefix() {
        System.out.println(StringUtils.removePrefix("abcdef", "abc"));
        System.out.println(StringUtils.isBlank(CommonConstants.Symbol.CRLF));
    }
}