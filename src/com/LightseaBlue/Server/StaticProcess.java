package com.LightseaBlue.Server;

import com.LightseaBlue.Servlet.ServletRequst;
import com.LightseaBlue.Servlet.ServletResponse;

public class StaticProcess implements Processor{

	@Override
	public void process(ServletRequst requse, ServletResponse resopnse) {
		((HttpResponse)resopnse).send();
	}

}
