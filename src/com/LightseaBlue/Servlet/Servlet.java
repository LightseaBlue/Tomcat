package com.LightseaBlue.Servlet;

public interface Servlet {
	//初始化
	public void init();

	// 销毁
	public void destory();

	public void service(ServletRequst requse,ServletResponse response);
}
