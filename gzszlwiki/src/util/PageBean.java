package util;

import java.util.List;

/**���ڰ���ʵ�ַ�ҳ����
 */
public class PageBean<T> {
	
	private final static int DEFAULT_PAGESIZE = 20;//Ĭ��ÿҳ��ʾ��Ŀ
	private int start ;//ÿҳ��ʼ��ʾ����Ŀ����
	private int pageSize;//ÿҳ��Ҫ��ʾ����Ŀ  
	private int totalRows;//�ܵ���ʾ��Ŀ	
	private int totalPage ;//��ҳ��
	private int currentPage ;//��ǰҳ
	private Boolean hasNextPage ;//�Ƿ�����һҳ
	
	private List<T> list = null; // ���һ�η��صļ�¼ 
	
	//�޲ι��캯��
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
			//��������ֵ����
			this.currentPage = currentPage ;
			this.pageSize = pageSize ;
		}else{
			//ֻ����currentPage����
			if(currentPage>0){
				this.currentPage = currentPage ;
				this.pageSize = DEFAULT_PAGESIZE;
			}
		}
		
	}
	
	//��ҳ��Ѱʱ�õ��Ĺ��캯��,û�д�pageSize
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
	
	//������ҳ��
	public int countTotalPage(int totalRows){
		return totalRows%this.pageSize==0?totalRows/this.pageSize:totalRows/this.pageSize+1;
	}
	
	//����ÿҳ��ʼ��ʾ����Ŀ����
	public void countStart(int totalPage){
		if(this.currentPage > totalPage || this.currentPage < 1 || this.currentPage == 0){
			this.currentPage = 1;
		}
		 this.start = this.pageSize*(this.currentPage-1);
	}
	
	//�����Ƿ�����һҳ
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

