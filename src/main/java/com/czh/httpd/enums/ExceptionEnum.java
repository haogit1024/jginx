package com.czh.httpd.enums;

import lombok.Getter;

import java.text.MessageFormat;

/**
 * @author czh
 * @data 2020/07/25
 */
@Getter
public enum ExceptionEnum {
	/**
	 * 1000 - 1999 为配置错误
	 * 1100 - 1100 为默认配置文件出错
	 * 2000 - 2999 为http错误
	 * 3000 - 3999 为程序错误
	 */
	DEFAULT_CONFIG_FILE_NOT_FOUND(1100, "默认配置文件没找到"),
	DEFAULT_CONFIG_CONTENT_IS_BLANK(1101, "默认配置文件内容为空"),
	DEFAULT_CONFIG_SERVER_PATH_IS_BLANK(1102, "默认配置文件server路径为空"),
	DEFAULT_CONFIG_SERVER_PATH_NOT_EXISTED_OR_NOT_DIR(1103, "serverPath {0} 不存在或不是一个文件夹"),
	DEFAULT_CONFIG_SERVER_PATH_CONTENT_IS_BLANK(1103, "serverPath {0} 内容为空"),


	;
	private final Integer code;
	private String message;
	private final String template;
	
	ExceptionEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
		this.template = message;
	}

	public ExceptionEnum format(Object... msgArgs) {
		this.message = MessageFormat.format(template, msgArgs);
		return this;
	}
}
