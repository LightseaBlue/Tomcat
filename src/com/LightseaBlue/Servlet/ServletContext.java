package com.LightseaBlue.Servlet;

import java.util.Map;

public interface ServletContext {

	// 获取所有servlet
	public Map<String, Servlet> getServlets();

	public Servlet getServlet(String name);

	public void setServlet(String name, Servlet s);

	public void setAttribute(String key, Object o);

	public Object getAttribut(String key);

}
