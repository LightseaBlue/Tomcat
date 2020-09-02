package com.LightseaBlue.Tool;

import java.io.IOException;
import java.net.Socket;

import com.LightseaBlue.Server.DynamicProcess;
import com.LightseaBlue.Server.HttpRequst;
import com.LightseaBlue.Server.HttpResponse;
import com.LightseaBlue.Server.Processor;
import com.LightseaBlue.Server.StaticProcess;
import com.LightseaBlue.Server.changliang;

public class ThreadR extends Thread {

	private boolean flag;
	private Socket s;

	public ThreadR(int i) {
		System.out.println("线程" + i + "运行");
	}

	@Override
	public synchronized void run() {
		while (true) {
			if (flag) {
				try {
//					changliang.logger.debug("监听到" + s.getLocalAddress());
					HttpRequst request = new HttpRequst(s.getInputStream());
					HttpResponse response = new HttpResponse(request, s.getOutputStream());
//					response.send();
					
					Processor p;
					if(request.getRequestURI().endsWith(".do")) {
						p=new DynamicProcess();
					}else {
						p=new StaticProcess();
					}
					
					p.process(request, response);
				} catch (IOException e) {
					flag = false;
					e.printStackTrace();
				}

				try {
					s.close();
					changliang.logger.debug(this.getName()+"已经重新准备。。。");
					flag=false;
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public synchronized void setFlag(boolean flag) {
		this.flag = flag;
		if (this.flag) {
			this.notifyAll();
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
