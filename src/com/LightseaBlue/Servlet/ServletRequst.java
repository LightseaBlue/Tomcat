package com.LightseaBlue.Servlet;

import java.util.Map;

public interface ServletRequst {
	public String getRealPath();

	// 获取请求中存储的信息
	public Object getAttribute(String key);

	public void setAttribute(String key, Object value);

	public Object getParamter(String key);

	public Map<String, Object> getParamterMap();

	/**
	 * 解析请求 
	 * 解析出uri 
	 * 解析出参数 解析出请求方式
	 */
	public void parse();

	public String getServerName();

	public int getServerPort();
}
