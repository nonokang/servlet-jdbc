package com.servlet.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>版权信息:</b> servlet+jdbc框架练习<br>
 * <b>功能描述:</b> 拦截器。主要用于初始化jdbc、访问拦截、超时拦截、日志记录等<br>
 * <b>版本历史: 0.0.1</b>
 * @author  wpk | 2018年1月31日 上午11:25:18 |创建
 */
//@WebFilter("/*")
public class FilterInit implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("=====销毁=====");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getServletPath(); 
//        System.out.println("url"+url);
		System.out.println("=========拦截处理========url:"+url);
        if(url.contains("/assets/") || url.contains(".html")){//不进行拦截的链接
        	chain.doFilter(request, response); 
        }else if(!req.isRequestedSessionIdValid()){
            System.out.println("页面过期");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }else if(null!=req.getSession() && null!=req.getSession().getAttribute("user")){
            System.out.println("user在session中");
            chain.doFilter(request, response);            
        }else{
            System.out.println("非法访问被过滤");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);                        
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("初始化mysql数据库......");
		new JDBCUtil();
	}

}
