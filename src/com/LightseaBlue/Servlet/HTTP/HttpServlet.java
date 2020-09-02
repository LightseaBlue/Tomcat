package com.LightseaBlue.Servlet.HTTP;

import com.LightseaBlue.Servlet.Servlet;
import com.LightseaBlue.Servlet.ServletRequst;
import com.LightseaBlue.Servlet.ServletResponse;

public abstract class HttpServlet implements Servlet {

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub

	}

	protected void doGet(HttpRequset requse, HttpResponse response) {
	}

	protected void doPost(HttpRequset requse, HttpResponse response) {
	}

	protected void doHead(HttpRequset requse, HttpResponse response) {
	}

	protected void doDelete(HttpRequset requse, HttpResponse response) {
	}

	public void service(HttpRequset requse, HttpResponse response) {
		String method = ((HttpRequset) requse).getMethod();

		// 调用的是子类方法
		if ("get".equalsIgnoreCase(method)) {
			doGet(((HttpRequset) requse), (HttpResponse) response);
		} else if ("post".equalsIgnoreCase(method)) {
			doPost(((HttpRequset) requse), (HttpResponse) response);
		} else if ("head".equalsIgnoreCase(method)) {
			doHead(((HttpRequset) requse), (HttpResponse) response);
		} else if ("delete".equalsIgnoreCase(method)) {
			doDelete(((HttpRequset) requse), (HttpResponse) response);
		}
	}

	@Override
	public void service(ServletRequst requse, ServletResponse response) {
		this.service((HttpRequset) requse, (HttpResponse) response);
	}

}
