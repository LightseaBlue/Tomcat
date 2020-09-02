package com.LightseaBlue.Servlet.HTTP;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.LightseaBlue.Servlet.Servlet;
import com.LightseaBlue.Servlet.ServletContext;

public class HttpContext implements ServletContext {
	// 修改方法是线程安全的
	private Map<String, Servlet> servlets = new Hashtable<String, Servlet>();
	private Map<String, Object> attributes = new HashMap<String, Object>();

	// 懒汉模式
	private static HttpContext hc;

	// 构造方法私有化
	private HttpContext() {
	}

	// 使用访问方法，获取唯一实例
	public synchronized static HttpContext getInstance() {
		if (hc == null) {
			hc = new HttpContext();
		}
		return hc;
	}

	@Override
	public Map<String, Servlet> getServlets() {
		return servlets;
	}

	@Override
	public Servlet getServlet(String name) {
		return servlets.get(name);
	}

	@Override
	public void setServlet(String name, Servlet s) {
		servlets.put(name, s);
	}

	@Override
	public void setAttribute(String key, Object o) {
		attributes.put(key, o);
	}

	@Override
	public Object getAttribut(String key) {
		return attributes.get(key);
	}

}
