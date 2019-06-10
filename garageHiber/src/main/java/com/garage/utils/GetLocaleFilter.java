package com.garage.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.CookieLocaleResolver;

public class GetLocaleFilter implements Filter {

	private ServletContext context;
	CookieLocaleResolver cookie;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.cookie = new CookieLocaleResolver();
		this.context = filterConfig.getServletContext();
		this.context.log("GetLocaleFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.getLocale();
		cookie.resolveLocale((HttpServletRequest)request);
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {

	}
}
