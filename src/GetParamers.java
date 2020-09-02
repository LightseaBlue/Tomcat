import com.LightseaBlue.Servlet.HTTP.HttpRequset;
import com.LightseaBlue.Servlet.HTTP.HttpResponse;
import com.LightseaBlue.Servlet.HTTP.HttpServlet;

public class GetParamers extends HttpServlet {
	@Override
	protected void doGet(HttpRequset requse, HttpResponse response) {
		String x = requse.getParamter("x").toString();
		System.out.println(x);
	}
}
