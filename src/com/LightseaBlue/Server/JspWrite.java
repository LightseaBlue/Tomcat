package com.LightseaBlue.Server;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.LightseaBlue.Servlet.ServletResponse;
import com.LightseaBlue.Servlet.HTTP.Cookie;

public class JspWrite extends PrintWriter{

	private ServletResponse response;

	public JspWrite(OutputStream out) {
		super(out);
	}

	public JspWrite(OutputStream out,ServletResponse response) {
		super(out);
		this.response=response;
	}
	
	
	public void println(String context) {
		long length=context.getBytes().length;
		StringBuffer sb=new StringBuffer();
		String contextType="text/html";
		
		String protocal200 = "HTTP/1.1 200 OK\r\nContent-Type: "+contextType+"\r\nContent-Length: "
		+ length + "\r\n\r\n";
		sb.append(protocal200);
		
		//拼接cookie
		Cookie[] cs=((HttpResponse)response).getCookies();
		if(cs!=null&&cs.length>0) {
			sb.append("   Set-Cookie:  ");
			for(Cookie c:cs) {
				sb.append(   c.toString());
			}
		}
		
		sb.append("\r\n\r\n");
		sb.append(context);
		
		super.println(sb.toString());
		super.flush();
	}
	
	public void println1( String content){
//		System.out.println("进来了。。");
		System.out.println(content);
		//协议的拼装...
		long length=content.getBytes().length;
		StringBuffer sb=new StringBuffer();
		
		String contentType="text/html";
		String protocal200 = "HTTP/1.0 200 OK\r\nContent-Type: "+contentType+"\r\nContent-Length: "
				+ length +"\r\n";
		sb.append(   protocal200 );
		
		//拼接cookie
		Cookie[] cs=((HttpResponse)this.response).getCookies();
		if( cs!=null&&cs.length>0){
			sb.append("Set-Cookie: ");
			for(  Cookie c:cs){
				if(c==null) {
//					sb.delete(sb.length()-2, sb.length());
					break;
				}
//				System.out.println("dd"+c.toString());
				sb.append(   c.toString());
			}
		}
		sb.append("\r\n\r\n");
		sb.append(   content);
		
//		System.out.println("aslkdjfkl");
		
//		System.out.println("sb          "+sb.toString());
		
		super.println(  sb.toString()  );
		super.flush();
		
	}
}
