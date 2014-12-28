package Filtre;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.AuthenticationBean;


//@WebFilter("/admin/*")
public class FiltreAdmin implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
	//	HttpServletRequest servletRequest=(HttpServletRequest) request;
	//	HttpServletResponse servletResponse =(HttpServletResponse) response;
	//	AuthenticationBean authenticationBean=(AuthenticationBean) servletRequest.getSession().getAttribute("authenticationBean");
	//	if(authenticationBean!=null && authenticationBean.isLoggedIn() && authenticationBean.getUserType().equals("admin")){
			chain.doFilter(request, response);
	//	}
	//	else{
	//		servletResponse.sendRedirect(servletRequest.getContextPath());
	//	}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
