import com.LightseaBlue.Server.HttpResponse;
import com.LightseaBlue.Server.JspWrite;
import com.LightseaBlue.Servlet.HTTP.Cookie;
import com.LightseaBlue.Servlet.HTTP.HttpRequset;
import com.LightseaBlue.Servlet.HTTP.HttpServlet;





public class HelloServlet2 extends HttpServlet {
	@Override
	protected void doGet(HttpRequset requse, com.LightseaBlue.Servlet.HTTP.HttpResponse response) {
		// TODO Auto-generated method stub
		doGet(requse, response);
	}
	
	@Override
	protected void doPost(HttpRequset requse, com.LightseaBlue.Servlet.HTTP.HttpResponse response) {

		Cookie c=new Cookie("name","zy");
		Cookie c2=new Cookie( "pwd","a");
		Cookie c3=new Cookie("age","20");
		response.addCookie(  c  );
		response.addCookie(  c2  );
		response.addCookie(  c3  );   ///   有一个集合存Cookie
		
		
		JspWrite pw=response.getJspWrite();  //   方法:  println();
											  //   PrintWriter一定要有一个response  构造方法( response
		pw.println1("<html><body>hello</body></html>");
	}
	
	
	
}
