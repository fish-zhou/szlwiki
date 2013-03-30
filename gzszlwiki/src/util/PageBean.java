package util;

import java.util.List;

/**用于帮助实现分页的类
 */
public class PageBean<T> {
	
	private final static int DEFAULT_PAGESIZE = 20;//默认每页显示条目
	private int start ;//每页开始显示的条目索引
	private int pageSize;//每页需要显示的条目  
	private int totalRows;//总的显示条目	
	private int totalPage ;//总页数
	private int currentPage ;//当前页
	private Boolean hasNextPage ;//是否有下一页
	
	private List<T> list = null; // 存放一次返回的记录 
	
	//无参构造函数
	public PageBean(){
		this.start = 0;
		this.pageSize = DEFAULT_PAGESIZE;
		this.totalRows = 0 ;
		this.totalPage = 1;
		this.currentPage = 1;
		this.hasNextPage = false;
	}
	
	public PageBean(int currentPage,int pageSize){
		this.list = null;
		this.start = 0;
		this.pageSize = DEFAULT_PAGESIZE;
		this.totalRows = 0 ;
		this.totalPage = 1;
		this.hasNextPage = false;
		if(currentPage>0&&pageSize >0){
			//传了两个值过来
			this.currentPage = currentPage ;
			this.pageSize = pageSize ;
		}else{
			//只传了currentPage过来
			if(currentPage>0){
				this.currentPage = currentPage ;
				this.pageSize = DEFAULT_PAGESIZE;
			}
		}
		
	}
	
	//分页查寻时用到的构造函数,没有传pageSize
	public PageBean(Integer currentPage){
		if(currentPage!=null){
			this.currentPage = currentPage ;
			this.pageSize = DEFAULT_PAGESIZE;
		}else{
			this.start = 0;
			this.pageSize = DEFAULT_PAGESIZE;
			this.totalRows = 0 ;
			this.totalPage = 1;
			this.currentPage = 1;	
		}		
	}
	
	//计算总页数
	public int countTotalPage(int totalRows){
		return totalRows%this.pageSize==0?totalRows/this.pageSize:totalRows/this.pageSize+1;
	}
	
	//计算每页开始显示的条目索引
	public void countStart(int totalPage){
		if(this.currentPage > totalPage || this.currentPage < 1 || this.currentPage == 0){
			this.currentPage = 1;
		}
		 this.start = this.pageSize*(this.currentPage-1);
	}
	
	//计算是否有下一页
	public void countHasNextPage(){
		if(this.currentPage < this.totalPage){
			this.hasNextPage = true;
		}else{
			this.hasNextPage = false;
		}
	}
//setters and getters
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public static int getDefaultPagesize() {
		return DEFAULT_PAGESIZE;
	}

	public Boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	
}

