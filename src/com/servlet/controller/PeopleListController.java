package com.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.servlet.model.PeopleModel;
import com.servlet.model.bean.People;
import com.servlet.utils.Page;

/**
 * Servlet implementation class ProprlListController
 */
@WebServlet("/peopleList.html")
public class PeopleListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
		String start = request.getParameter("page");//起始页
		String limit = request.getParameter("limit");//展示总数据条数
		String searchName = request.getParameter("searchName");
		Enumeration<String> ens = request.getAttributeNames();
		while(ens.hasMoreElements()){
            String value = (String)ens.nextElement();//调用nextElement方法获得元素
            System.out.print(value);
        }
		
		PeopleModel pm = new PeopleModel();
		Page page = new Page(Integer.parseInt(start), Integer.parseInt(limit));
		List<People> list = pm.getList(page, searchName);
		
		JSONObject job = new JSONObject();
		job.put("code", 0);
		job.put("count", pm.getCount(searchName));
		job.put("data", list);
		
		// 输出json数据
		PrintWriter out = response.getWriter();
		out = response.getWriter();
		out.println(job);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
