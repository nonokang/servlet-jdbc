package com.servlet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.servlet.model.bean.People;
import com.servlet.utils.JDBCUtil;
import com.servlet.utils.Page;

/**
 * <b>版权信息:</b> servlet+jdbc框架练习<br>
 * <b>功能描述:</b> 测试模型<br>
 * <b>版本历史: 0.0.1</b>
 * @author  wpk | 2018年2月6日 上午10:53:12 |创建
 */
public class PeopleModel {
	
	/**
	 * <b>描述：</b> 获取数据总条数
	 * @author wpk | 2018年2月6日 上午10:53:25 |创建
	 * @return int
	 */
	public int getCount(String name){
		int count = 0;
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null; 
		try {
			conn = JDBCUtil.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select count(1) count from people where opera<>'-1' ");
			if(null != name){
				sb.append("and name like '%").append(name).append("%' ");
			}
			pre = conn.prepareStatement(sb.toString());
			res = pre.executeQuery();
			while(res.next()){
				count = res.getInt("count");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(res, pre, conn);
		}
		return count;
	}

	/**
	 * <b>描述：</b> 获取分页列表
	 * @author wpk | 2018年2月6日 上午10:53:44 |创建
	 * @param page
	 * @return List<People>
	 */
	public List<People> getList(Page page, String name){
		List<People> items = new ArrayList<People>();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null; 
		try {
			conn = JDBCUtil.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select * from people where opera<>'-1' ");
			if(null != name){
				sb.append("and name like '%").append(name).append("%' ");
			}
			sb.append("limit ").append(page.getStartNum()).append(",").append(page.getPagesize());
			pre = conn.prepareStatement(sb.toString());
			res = pre.executeQuery();
			while(res.next()){
				People p = new People();
				p.setId(res.getInt("id"));
				p.setName(res.getString("name"));
				p.setAge(res.getInt("age"));
				p.setOpera(res.getString("opera"));
				p.setRemark(res.getString("remark"));
				items.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(res, pre, conn);
		}
		return items;
	}
	
	/**
	 * <b>描述：</b> 通过ID获取详细信息
	 * @author wpk | 2018年2月6日 上午10:54:03 |创建
	 * @param id
	 * @return People
	 */
	public People getById(int id){
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null; 
		People p = new People();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from people where id = "+id;
			pre = conn.prepareStatement(sql);
			res = pre.executeQuery();
			while(res.next()){
				p.setId(res.getInt("id"));
				p.setName(res.getString("name"));
				p.setAge(res.getInt("age"));
				p.setOpera(res.getString("opera"));
				p.setRemark(res.getString("remark"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(res, pre, conn);
		}
		return p;
	}
	
	/**
	 * <b>描述：</b> 通过id删除指定信息
	 * @author wpk | 2018年2月6日 上午10:54:25 |创建
	 * @param id
	 * @return void
	 */
	public boolean delById(int id){
		boolean flag = true;
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update people set opera='-1' where id="+id;
			pre = conn.prepareStatement(sql);
			int res = pre.executeUpdate();
			if(res > 0){
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally{
			JDBCUtil.close(null, pre, conn);
		}
		return flag;
	}
	
	/**
	 * <b>描述：</b> 更新数据
	 * @author wpk | 2018年2月6日 下午5:28:40 |创建
	 * @param p
	 * @return
	 * @return boolean
	 */
	public boolean updById(People p){
		boolean flag = true;
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JDBCUtil.getConnection();
			String s = "update people set name='%s',age=%s,opera='%s',remark='%s' where id=%s";
			String sql = String.format(s, p.getName().toString(),p.getAge(),"0",p.getRemark(),p.getId());
			pre = conn.prepareStatement(sql);
			int res = pre.executeUpdate();
			if(res > 0){
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally{
			JDBCUtil.close(null, pre, conn);
		}
		return flag;
	}
	
	/**
	 * <b>描述：</b> 保存数据
	 * @author wpk | 2018年2月6日 下午5:29:28 |创建
	 * @param p
	 * @return boolean
	 */
	public boolean save(People p){
		boolean flag = true;
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JDBCUtil.getConnection();
			String s = "insert into people (name,age,opera,remark) values('%s',%s,'%s','%s')";
			String sql = String.format(s, p.getName(),p.getAge(),"0",p.getRemark());
			pre = conn.prepareStatement(sql);
			int res = pre.executeUpdate();
			if(res > 0){
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally{
			JDBCUtil.close(null, pre, conn);
		}
		return flag;
	}
	
}
