package cn.yhd.hbase;

import java.util.List;

/**  
 * @author myx
 * @createTime 2015年4月14日 下午2:20:00  
 * 
 */
public class Pagination <T>{
	private int pageNo;
	
	private int pageSize;
	
	private int totalCount;
	
	private int totalPage;
	
	private List<T> rows;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
