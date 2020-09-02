package com.LightseaBlue.Server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.LightseaBlue.Servlet.HTTP.HttpContext;
import com.LightseaBlue.Servlet.HTTP.HttpRequset;

public class HttpRequst implements HttpRequset {
	private String method; // 请求方法
	private String protocal;// 版本协议
	private String serverName;// 服务器名
	private int serverPort;// 端口
	private String requestURI;// 资源的地址

	private String requestParamers;// 地址栏后的参数

	private String requestURL;// 绝对路径
	private String contextPath;// 项目上下文路径

	private String realPath = System.getProperty("user.dir") + File.separatorChar + "webapps";

	private InputStream in;

	// 参数存储
	private Map<String, Object> paramters = new HashMap<String, Object>();

	public HttpRequst(InputStream in) {
		this.in = in;
		setText(readFromInputStream());
	}

	public void setText(String allText) {
//		System.out.println(allText);
		if (allText == null || "".equals(allText)) {
			changliang.logger.error("未获取到http请求头。。。。");
			return;
		}

//		System.out.println(allText);
		StringTokenizer st = new StringTokenizer(allText);

		// 获取全部路径包括get中的参数
		String allUri = null;
		if (st.hasMoreTokens()) {
			method = st.nextToken();
			allUri = st.nextToken();
			protocal = st.nextToken();
//			System.out.println("adsfasdfasdf"+method+requestURI+protocal);
		}

		if (allUri.indexOf("?") != -1) {
			requestParamers = allUri.substring(allUri.lastIndexOf("?") + 1);
			requestURI = allUri.substring(0, allUri.lastIndexOf("?"));

			String[] all = requestParamers.split("\\&");
			for (String str : all) {
				try {
					String[] a = str.split("\\=");
					paramters.put(a[0], a[1]);
				} catch (Exception e) {
					changliang.logger.error("参数有问题。。。。。");
				}
			}
		} else {
			requestURI = allUri;
		}
		
//		System.out.println(protocal);
		if(method.equalsIgnoreCase("post")) {
			String ps=allText.substring(allText.lastIndexOf("\r\n\r\n")+4);
			System.out.println(ps);
			if(ps!=null&&ps.length()>0) {
				String[] ss=ps.split("\\&");
				for(String s:ss) {
					String[] paire=s.split("\\=");
					paramters.put(paire[0], paire[1]);
				}
			}
		}
	}

	private String readFromInputStream() {
		String protocal = null;
		StringBuffer sb = new StringBuffer(1024 * 10);
		int length = -1;
		byte[] bs = new byte[1024 * 10];
		try {
			length = this.in.read(bs);
		} catch (IOException e) {
			e.printStackTrace();
			length = -1;
		}
		for (int j = 0; j < length; j++) {
			sb.append((char) bs[j]);
		}
		protocal = sb.toString();
		return protocal;
	}

	public String getAllText() {
		StringBuffer sb = new StringBuffer(1024 * 10);

		int length = -1;
		byte[] b = new byte[1024 * 10];

		try {
			while ((length = in.read(b)) != -1) {
				String str = new String(b, 0, length);
//				System.out.println("asdfdsaf"+str);
				sb.append(str);
				System.out.println(sb);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("sadf" + sb.toString() + "fasdf" + sb);
		return sb.toString();
	}

	private Map<String, Object> attribute = new HashMap<String, Object>();

	@Override
	public Object getAttribute(String key) {
		return attribute.get(key);
	}

	@Override
	public void setAttribute(String key, Object value) {
		attribute.put(key, value);
	}

	@Override
	public Object getParamter(String key) {
		return paramters.get(key);
	}

	@Override
	public Map<String, Object> getParamterMap() {
		return paramters;
	}

	@Override
	public void parse() {
		// TODO Auto-generated method stub

	}

	@Override
	public HttpContext getContext() {
		return HttpContext.getInstance();
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getProtocal() {
		return protocal;
	}

	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getRequestParamers() {
		return requestParamers;
	}

	public void setRequestParamers(String requestParamers) {
		this.requestParamers = requestParamers;
	}

}
