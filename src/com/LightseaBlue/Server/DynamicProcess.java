package com.LightseaBlue.Server;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.LightseaBlue.Servlet.Servlet;
import com.LightseaBlue.Servlet.ServletRequst;
import com.LightseaBlue.Servlet.ServletResponse;
import com.LightseaBlue.Servlet.HTTP.HttpContext;
import com.LightseaBlue.Servlet.HTTP.HttpRequset;
import com.LightseaBlue.Servlet.HTTP.HttpResponse;
import com.LightseaBlue.Servlet.HTTP.HttpServlet;

public class DynamicProcess implements Processor {

	@Override
	public void process(ServletRequst requse, ServletResponse resopnse) {
		// 取出请求的名字 hello.do
		String uri = ((HttpRequst) requse).getRequestURI();
		// System.out.println(uri);
		// 取出文件名 hello
		String servletName = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		Servlet s;
		HttpContext hc = HttpContext.getInstance();

		if (hc.getServlet(servletName) != null) {
			s = hc.getServlet(servletName);
		} else {

			// System.out.println("文件名"+servletName);
			// 动态加载字节码
			URL[] urls = new URL[1];

			try {
				urls[0] = new URL("file", null, requse.getRealPath());
				// 类加载器 自动扫描路径下的文件
				URLClassLoader uc = new URLClassLoader(urls);
				Class<?> c = uc.loadClass(servletName);

				// 使用反射加载类
				Object o = c.newInstance();

				if (o != null && o instanceof Servlet) {
					s = (Servlet) o;
					//System.out.println("jinlaile");
					s.init();

					((HttpServlet) s).service((HttpRequset) requse, (HttpResponse) resopnse);
				}
				uc.close();

			} catch (Exception e) {
				String str = e.toString();
				String protocal500 = get500(str.length());
				PrintWriter pw = resopnse.getWriter();
				pw.write(protocal500);
				pw.write(str);
				pw.flush();
			}
		}
	}

	public String get500(long bodylength) {
		String protocal404 = "HTTP/1.1 500 File Not Found\r\nContent-Type: text/html\r\nContent-Length: " + bodylength
				+ "\r\n\r\n";
		return protocal404;
	}

}
