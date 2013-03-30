package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) res;
		if(request != null){
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("username");
			if(obj != null &&!"".equals(obj.toString())){
				chain.doFilter(req, res);
			}else{
				//登录页面
				String loginPage = request.getContextPath()+"/login.jsp";
				response.sendRedirect(loginPage);
			}
		}
	}

	public void init(FilterConfig chain) throws ServletException {
		
	}

	public void destroy() {}
}