package com.LightseaBlue.Servlet.HTTP;

import com.LightseaBlue.Servlet.ServletRequst;

public interface HttpRequset extends ServletRequst {

	public String getMethod();

//	public String getRequstURI();

	public HttpContext getContext();
}
