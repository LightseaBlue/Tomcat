package com.LightseaBlue.Tool;

import java.net.Socket;
import java.util.Vector;

public class ThreadM {
	private Vector<Thread> v = new Vector<Thread>();

	public ThreadM(int num) {
		for (int i = 0; i < num; i++) {
			ThreadR r = new ThreadR(i + 1);
			r.setName("线程" + (i + 1));
			v.add(r);
			r.start();
		}
	}

	public void work(Socket s) {
		int num = 0;
		for (int i = 0; i < v.size(); i++) {
			ThreadR r = (ThreadR) v.get(i);

			if (!r.isFlag()) {
				r.setS(s);
				r.setFlag(true);
				return;
			}

			num++;

		}

		System.out.println("--------------------------------");
		System.out.println("              线程不够了                                   ");
		System.out.println("--------------------------------");

		if (num == v.size()) {
			for (int j = num; j < num + 5; j++) {
				ThreadR t = new ThreadR(j);
				t.setName("线程" + (j+1));
				v.add(t);
				t.start();
			}
		}
		
		work(s);
	}

}
