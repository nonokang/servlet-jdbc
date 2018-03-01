package com.servlet.utils;

/**
 * <b>版权信息:</b> servlet+jdbc框架练习<br>
 * <b>功能描述:</b> 自定义分页<br>
 * <b>版本历史: 0.0.1</b>
 * @author  wpk | 2018年2月6日 下午9:37:45 |创建
 */
public class Page {
	
	private int pageindex;
	private int startNum;
	private int pagesize;
	
	public Page(int pageindex, int pagesize){
		this.pageindex = pageindex;
		this.pagesize = pagesize;
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}
	
	public int getStartNum() {
		this.startNum = (this.pageindex * this.pagesize)-this.pagesize;
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
}
