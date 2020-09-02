import com.LightseaBlue.Server.JspWrite;
import com.LightseaBlue.Servlet.HTTP.Cookie;
import com.LightseaBlue.Servlet.HTTP.HttpRequset;
import com.LightseaBlue.Servlet.HTTP.HttpResponse;
import com.LightseaBlue.Servlet.HTTP.HttpServlet;

public class Hello extends HttpServlet{
	public static void main(String[] args) {
		StringBuffer sb=new StringBuffer();
		sb.append("asdf ");
		sb.delete(sb.length()-2, sb.length());
		System.out.println(sb);
	}
	
	public Hello() {
		super();
		System.out.println("构造方法");
	}
	
	@Override
	public void init() {
		super.init();
		System.out.println("init");
	}
	
	@Override
	public void service(HttpRequset requse, HttpResponse response) {
		super.service(requse, response);
		System.out.println("初始化方法");
	}
	
	@Override
	protected void doGet(HttpRequset requse, HttpResponse response) {
		System.out.println("doget调用转到都post");
		doPost(requse, response);
	}
	
	@Override
	protected void doPost(HttpRequset requse, HttpResponse response) {
		System.out.println("post调用了");
		int i=5/2;
		String str="Hello World"+i;
		
//		Map<String, Object> map=requse.getParamterMap();
//		
//		Set<Entry<String, Object>> s=map.entrySet();
//		
//		for(Entry<String, Object> e:s) {
//			System.out.println("键"+e.getKey());
//			System.out.println("值"+e.getValue());
//		}
//		
//		PrintWriter pw=response.getWriter();
//		StringBuffer sb=new StringBuffer();
//		sb.append("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: "
//		+ str.length() + "\r\n\r\n");
//		pw.write(sb.toString());
//		pw.write(str);
//		pw.flush();
//		pw.close();
		
		Cookie c1=new Cookie("sadf", "a");
		Cookie c2=new Cookie("b","b");
		
		response.addCookie(c1);
		response.addCookie(c2);
		
		JspWrite jw=response.getJspWrite();
		
		jw.println1("<html><body>hello</body></html>");

	}
}
