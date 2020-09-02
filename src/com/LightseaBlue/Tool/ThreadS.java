package com.LightseaBlue.Tool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.LightseaBlue.Server.changliang;

public class ThreadS {
	public static void main(String[] args) throws IOException {
		ServerSocket ss=new ServerSocket(getPort());
		
		boolean flag=true;
		ThreadM m=new ThreadM(10);
		
		while(flag) {
			Socket s=ss.accept();
			m.work(s);
		}
		
		ss.close();
	}
	
	private static int getPort() {
		List<Integer> list = new ArrayList<Integer>();
		// 通过DocumentBuilderFactory建立xml解释器
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 通过解释器创建一个可以加载并生成xml的DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 通过文件构造器加载并生成一颗xml树，document对象
			org.w3c.dom.Document doc = builder.parse(changliang.SERVERCONFIG);
			// 通过document遍历这棵树，并通过标签获取内容
			NodeList nl = doc.getElementsByTagName("Connector");
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				System.out.println(node.getNodeName() + "     " + node.getNodeValue());
				list.add(Integer.parseInt(node.getAttribute("port")));

			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list.get(0);
	}
}
