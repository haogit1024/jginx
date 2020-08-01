package com.czh.httpd.enums;

/**
 * @author czh
 * @data 2020/07/25
 */

public enum ExceptionEnum {
	;
	private final Integer code;
	private final String message;
	private final String template;
	
	ExceptionEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
		this.template = message;
	}
}
