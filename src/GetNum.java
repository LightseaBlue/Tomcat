import java.io.PrintWriter;

import com.LightseaBlue.Servlet.HTTP.HttpContext;
import com.LightseaBlue.Servlet.HTTP.HttpRequset;
import com.LightseaBlue.Servlet.HTTP.HttpResponse;
import com.LightseaBlue.Servlet.HTTP.HttpServlet;

public class GetNum extends HttpServlet {

	public static void main(String[] args) {
		String x = "mdy100";
		String[] a = x.split("\\?");
		System.out.println(a.length);
		for (String str : a) {
			System.out.println(str);
		}
	}

	@Override
	protected void doGet(HttpRequset requse, HttpResponse response) {
		HttpContext hc = requse.getContext();
		int num = 0;

		if (hc.getAttribut("num") != null) {
			num = Integer.parseInt(hc.getAttribut("num").toString());
		}

		num++;
		hc.setAttribute("num", num);

		System.out.println("访问次数为：" + num);

		String str = "Num:" + num;

		PrintWriter pw = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: " + str.length() + "\r\n\r\n");
		pw.write(sb.toString());
		pw.write(str);
		pw.flush();
		pw.close();
	}
}
