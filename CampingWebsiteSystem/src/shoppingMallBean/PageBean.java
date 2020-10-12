package shoppingMallBean;

import java.util.List;

public class PageBean<T> {

	private int pageSize; // 顯示多少條

	private int currentPage; // 現在第幾頁

	private int totalRecord; // 一共多少紀錄

	private int totalPage; // 一共多少頁

	private List<T> dataList; // 要顯示的資料
	
	private int maxsize;

	public int getMaxsize() {
		return maxsize;
	}

	public void setMaxsize(int maxsize) {
		this.maxsize = maxsize;
	}

	private List<T> sourceList; // 原始資料

	public PageBean(int pageNum, int pageSize, List<T> sourceList) {
		if (sourceList == null || sourceList.isEmpty()) {
			return;
		}

		this.sourceList = sourceList;

		this.totalRecord = sourceList.size();

		// 每頁多少
		this.pageSize = pageSize;

		// 總頁數
		this.totalPage = this.totalRecord / this.pageSize;
		if (this.totalRecord % this.pageSize != 0) {
			this.totalPage = this.totalPage + 1;
		}

		// 現在多少
		this.currentPage = this.totalPage < pageNum ? this.totalPage : pageNum;

		// 起始索引
		int fromIndex = this.pageSize * (this.currentPage - 1);

		// 结束索引
		int toIndex = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord
				: this.pageSize * this.currentPage;

		this.dataList = sourceList.subList(fromIndex, toIndex);
	}

	public PageBean() {

	}

	public PageBean(int pageSize, int currentPage, int totalRecord, int totalPage, List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public List<T> getSourceList() {
		return sourceList;
	}

	public void setSourceList(List<T> sourceList) {
		this.sourceList = sourceList;
	}

}
