package com.servlet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <b>版权信息:</b> servlet+jdbc框架练习<br>
 * <b>功能描述:</b> 数据库连接<br>
 * <b>版本历史: 0.0.1</b>
 * @author  wpk | 2018年1月31日 上午11:44:22 |创建
 */
public class JDBCUtil {
	
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>(); 
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e){
			String msg = String.format("加载mysql驱动失败，找不到驱动类：%s",e.getMessage());
			System.out.println(msg);
		}
	}
	
	/**
	 * <b>描述：</b> 获取连接
	 * @author wpk | 2018年1月31日 下午2:17:16 |创建
	 * @return Connection
	 */
    public static Connection getConnection(){  
        Connection conn = connectionHolder.get();  
        try {
	        if(conn == null || conn.isClosed()){  
	            	String url = "jdbc:mysql://localhost:3306/wpk_demo?useUnicode=true&characterEncoding=utf8";
	            	String userName = "root";
	            	String paw = "root";
	                conn = DriverManager.getConnection(url, userName, paw);  
	                connectionHolder.set(conn);   
	        }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        return conn;                                      
    }  
    
    /**
     * <b>描述：</b> 关闭数据源
     * @author wpk | 2018年2月6日 上午10:33:21 |创建
     * @param res
     * @param pre
     * @param conn
     * @return void
     */
    public static void close(ResultSet res, PreparedStatement pre, Connection conn){
		try {
	    	if(null != res){
				res.close();
	    	}
	    	if(null != pre){
	    		pre.close();
	    	}
	    	if(null != conn){
	    		Connection oldconn = connectionHolder.get();
	    		if(oldconn.equals(conn)){//判断是否是同一个对象
	    			oldconn.close();
	    			connectionHolder.remove();
	    		}else{
	    			conn.close();
	    		}
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
