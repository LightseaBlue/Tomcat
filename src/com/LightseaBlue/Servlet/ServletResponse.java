package com.LightseaBlue.Servlet;

import java.io.PrintWriter;

public interface ServletResponse {
	//获取输出字符流
	public PrintWriter getWriter();
	
	//获取输出类型
	public String getContentType();

}
