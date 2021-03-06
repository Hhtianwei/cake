package com.tim.cake.online.common.page;

public class Pagination
{
	private int pageSize;
	private int currentPage;
	private int totalResults;
	private int totalPages;

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getTotalResults()
	{
		return totalResults;
	}

	public void setTotalResults(int totalResults)
	{
		this.totalResults = totalResults;
	}

	public int getTotalPages()
	{
		int temp = totalResults % pageSize;
		int tempResult = totalResults / pageSize;
		this.totalPages = (temp == 0) ? tempResult : (tempResult + 1);
		return this.totalPages;
	}

	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}


}
