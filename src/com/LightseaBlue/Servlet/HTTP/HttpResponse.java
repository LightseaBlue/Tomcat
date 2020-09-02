package com.LightseaBlue.Servlet.HTTP;

import com.LightseaBlue.Server.JspWrite;
import com.LightseaBlue.Servlet.ServletResponse;

public interface HttpResponse extends ServletResponse {
	//输出重定向方法
	public void sendRedirect();
	
	public Cookie[] getCookies();
	public JspWrite getJspWrite();
	public void addCookie(Cookie c);
	
}
