package com.LightseaBlue.Server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.LightseaBlue.Servlet.HTTP.Cookie;

public class HttpResponse implements com.LightseaBlue.Servlet.HTTP.HttpResponse {
	private HttpRequst requst;
	private OutputStream out;
	private String contentType;

	public HttpResponse(HttpRequst request, OutputStream out) {
		this.requst = request;
		this.out = out;
	}

	public void send() {
		File f = new File(requst.getRealPath(), requst.getRequestURI());
		String responseHead = null;
		byte[] responseText = null;

		if (f.exists()) {
			responseHead = get200(f.length());
			responseText = readFile(f);
		} else {
			File file = new File(requst.getRealPath(), "\\wwt\\404.html");
			responseHead = get404(file.length());
			responseText = readFile(f);
			contentType = "html";
			changliang.logger.error("没有找到相应的请求体文件。。。。" + requst.getRealPath() + requst.getRequestURI());
		}

		try {
			out.write(responseHead.getBytes());
			out.flush();
			out.write(responseText);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String get404(long bodylength) {
		String protocal404 = "HTTP/1.1 404 File Not Found\r\nContent-Type: text/html\r\nContent-Length: " + bodylength
				+ "\r\n\r\n";
		return protocal404;
	}

	public String get200(long bodylength) {
		String protocal200 = null;
		String fileExtension = "";

		fileExtension = requst.getRequestURI().substring(requst.getRequestURI().lastIndexOf('.') + 1);

//		System.out.println("截取出的名字"+fileExtension);

		contentType = fileExtension;

		if ("JPG".equalsIgnoreCase(fileExtension) || "JPEG".equalsIgnoreCase(fileExtension)) {
			protocal200 = "HTTP/1.1 200 OK\r\nContent-Type: image/JPEG\r\nContent-Length: " + bodylength + "\r\n\r\n";
		} else if ("PNG".equalsIgnoreCase(fileExtension)) {
			protocal200 = "HTTP/1.1 200 OK\r\nContent-Type: image/PNG\r\nContent-Length: " + bodylength + "\r\n\r\n";
		} else if ("json".equalsIgnoreCase(fileExtension)) {
			protocal200 = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-Length: " + bodylength
					+ "\r\n\r\n";
		} else if ("css".equalsIgnoreCase(fileExtension)) {
			protocal200 = "HTTP/1.1 200 OK\r\nContent-Type: text/css\r\nContent-Length: " + bodylength + "\r\n\r\n";
		} else if ("js".equalsIgnoreCase(fileExtension)) {
			protocal200 = "HTTP/1.1 200 OK\r\nContent-Type: text/javascript\r\nContent-Length: " + bodylength
					+ "\r\n\r\n";
		} else {
			protocal200 = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: " + bodylength + "\r\n\r\n";
		}

		return protocal200;
	}

	private byte[] readFile(File f) {
		FileInputStream fis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();// 字节数组输入流

		try {
			fis = new FileInputStream(f);
			byte[] bs = new byte[1024];
			int length = -1;
			while ((length = fis.read(bs, 0, bs.length)) != -1) {
				baos.write(bs, 0, length);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 一次性的从所有内存中读取所有的字节数组返回
		return baos.toByteArray();
	}

	public String getFileText(File f) {
		StringBuffer sb = new StringBuffer(1024 * 20);
		InputStream is = null;
		try {
//			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			is = new FileInputStream(f);
			int length = -1;
			byte[] b = new byte[1024 * 10];

			while ((length = is.read(b, 0, b.length)) != -1) {
				sb.append(new String(b, 0, length));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	@Override
	public PrintWriter getWriter() {
		PrintWriter pw = new PrintWriter(out);
		return pw;
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	@Override
	public void sendRedirect() {
		// TODO Auto-generated method stub

	}

	private Cookie[] c = new Cookie[50];
	int index = 0;

	@Override
	public Cookie[] getCookies() {
		return c;
	}

	@Override
	public JspWrite getJspWrite() {
		JspWrite jw = new JspWrite(out, this);
		return jw;
	}

	@Override
	public void addCookie(Cookie c) {
		if (index >= this.c.length) {
			return;
		}
		this.c[index] = c;
		index++;
	}

}
