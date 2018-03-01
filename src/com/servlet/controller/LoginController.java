package com.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * <b>版权信息:</b> servlet+jdbc框架练习<br>
 * <b>功能描述:</b> <br>
 * <b>版本历史: 0.0.1</b>
 * @author  wpk | 2018年1月31日 上午11:26:39 |创建
 */
@WebServlet("/login.html")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String str = request.getContextPath();
//		System.out.println(str);
		response.setContentType("text/html");
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);//（页面跳转）
//		response.sendRedirect("/servlet-jdbc/user");//重定向（注意url地址已变更，可以跳转至其它网站，如：http://www.bai.com）
//		request.getRequestDispatcher("/user").forward(request, response);//重定向（注意url地址不变，只能在本服务上跳转）
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(String.format("登录名：%s \t 密码：%s", username, password));

		JSONObject job = new JSONObject();
		job.put("statusCode", 200);
		PrintWriter out = response.getWriter();
		// 输出json数据
		out = response.getWriter();
		out.println(job);
		out.flush();
	    out.close();
	}

}
