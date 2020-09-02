package com.LightseaBlue.Server;

import java.security.acl.LastOwnerException;

import org.apache.log4j.Logger;

public class changliang {
	public static void main(String[] args) {
		String a="POST /Hello.do HTTP/1.1\r\n" + 
				"Host: localhost:8081\r\n" + 
				"Connection: keep-alive\r\n" + 
				"Content-Length: 19\r\n" + 
				"Cache-Control: max-age=0\r\n" + 
				"Origin: http://localhost:8081\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"Content-Type: application/x-www-form-urlencoded\r\n" + 
				"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36\r\n" + 
				"Sec-Fetch-Dest: document\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" + 
				"Sec-Fetch-Site: same-origin\r\n" + 
				"Sec-Fetch-Mode: navigate\r\n" + 
				"Sec-Fetch-User: ?1\r\n" + 
				"Referer: http://localhost:8081/index.html\r\n" + 
				"Accept-Encoding: gzip, deflate, br\r\n" + 
				"Accept-Language: zh-CN,zh;q=0.9\r\n" + 
				"\r\n" + 
				"name=20&sex=a&num=b";
		
		String x=a.substring(a.lastIndexOf("\r\n\r\n")+4);
		
		System.out.println(x);
	}
	public final static Logger logger=Logger.getLogger(changliang.class);
	
	public final static String SERVERCONFIG="conf/server.xml";
}
