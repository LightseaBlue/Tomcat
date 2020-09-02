package com.LightseaBlue.Server;

import com.LightseaBlue.Servlet.ServletRequst;
import com.LightseaBlue.Servlet.ServletResponse;

/**
 * 静态动态资源分发
 * @author fox11
 *
 */
public interface Processor {

	public void process(ServletRequst requse,ServletResponse resopnse);
}
