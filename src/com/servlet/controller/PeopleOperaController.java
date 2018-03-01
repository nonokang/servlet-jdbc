package com.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.servlet.model.PeopleModel;
import com.servlet.model.bean.People;

/**
 * Servlet implementation class PeopleOpera
 */
@WebServlet("/peopleOpera.html")
public class PeopleOperaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			PeopleModel pm = new PeopleModel();
			People bean = pm.getById(Integer.parseInt(id));
			request.setAttribute("bean", bean);
		}
		request.getRequestDispatcher("/WEB-INF/views/people/peopleEdit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String remark = request.getParameter("remark");
		boolean flag = true;
		JSONObject job = new JSONObject();
		PeopleModel pm = new PeopleModel();
		try {
			People p = new People();
			p.setAge(Integer.parseInt(age));
			p.setName(name);
			p.setRemark(remark);
			if(null == id || "".equals(id)){//新增
				flag = flag && pm.save(p);
			}else{//修改
				p.setId(Integer.parseInt(id));
				flag = flag && pm.updById(p);
			}
		} catch (Exception e) {
			flag = false;
			job.put("msg", String.format("操作失败：%s", e.getMessage()));
		}
		job.put("success", flag);
		PrintWriter out = response.getWriter();
		out = response.getWriter();
		out.println(job);
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
		String ids = request.getParameter("ids");
		boolean flag = true;
		JSONObject job = new JSONObject();
		try {
			if(ids!=null && !"".equals(ids)){
				PeopleModel pm = new PeopleModel();
				String[] _ids = ids.split(",");
				for(String id : _ids){
					if(null == id || "".equals(id)) continue;
					flag = flag && pm.delById(Integer.parseInt(id));
				}
			}else{
				throw new NullPointerException(String.format("参数id为空！"));
			}
		} catch (Exception e) {
			flag = false;
			job.put("msg", String.format("操作失败：%s", e.getMessage()));
		}
		job.put("success", flag);
		PrintWriter out = response.getWriter();
		out = response.getWriter();
		out.println(job);
	}

}
