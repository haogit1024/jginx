package com.czh.httpd.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzh
 */
public class StringUtils {
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 驼峰命名法转符号分隔命名法
     * @param camelCase
     * @param symbol
     * @return
     */
    public static String camelCaseToSymbolCase(String camelCase, String symbol) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(camelCase);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, symbol + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static String removePrefix(String content, String prefix) {
        if (isBlank(content) || (prefix == null || prefix.length() == 0)) {
            return content;
        }
        int preFixLength = prefix.length();
        if (content.length() < preFixLength) {
            return content;
        }
        String realPrefix = content.substring(0, preFixLength);
        if (realPrefix.equals(prefix)) {
            return content.substring(preFixLength);
        } else {
            return content;
        }
    }

    public static String removePostfix(String content, String postfix) {
        if (isBlank(content) || (postfix == null || postfix.length() == 0)) {
            return content;
        }
        int postfixLength = postfix.length();
        int contentLength = content.length();
        if (contentLength < postfixLength) {
            return content;
        }
        String realPostfix = content.substring(content.length() - postfixLength);
        if (realPostfix.equals(postfix)) {
            return content.substring(0, contentLength - postfixLength);
        } else {
            return content;
        }
    }
}
