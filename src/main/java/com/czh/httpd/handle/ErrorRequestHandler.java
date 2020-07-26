package com.czh.httpd.handle;

import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.response.Response;
import com.czh.httpd.response.ResponseFactory;

/**
 * 错误请求处理器
 * @author czh
 * @data 2020/07/25
 */
public class ErrorRequestHandler implements IRequestHandler {
	private BaseRequestHeader requestHeader;

	private final String errorMsg;

	public ErrorRequestHandler(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public void setRequest(String request) throws IllegalAccessError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRequest(BaseRequestHeader requestHeader, String requestContent) {
		this.requestHeader = requestHeader;
	}

	@Override
	public Response getResponse() {
		// TODO Auto-generated method stub
		return ResponseFactory.getErrorResponse(errorMsg, requestHeader.getCookie());
//		return null;
	}

}
