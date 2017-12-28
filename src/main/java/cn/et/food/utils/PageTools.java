package cn.et.food.utils;

import java.util.List;

/**
 * ��ҳ�Ĳ���
 * @author Administrator
 *
 */
public class PageTools {
	/**
	 * �������
	 * @param curPage ҳ�洫��ĵ�ǰҳ
	 * @param totalCount ��ݿ��ѯ���ܼ�¼��
	 * @param pageCount ÿҳ��ʾ������
	 */
	public PageTools(Integer curPage,Integer totalCount,Integer pageCount){
		this.curPage=curPage;
		this.total=totalCount;
		this.pageCount=pageCount==null?10:this.pageCount;
		this.prePage=curPage==1?1:curPage-1;
		this.totalPage=totalCount%this.pageCount==0?totalCount/this.pageCount:totalCount/this.pageCount+1;
		this.nextPage=curPage==totalPage?totalPage:curPage+1;
		this.startIndex=(curPage-1)*this.pageCount+1;
		this.endIndex=curPage*this.pageCount;
	}
	/**
	 * ��ǰҳ����̬��ҳ�洫�ݣ�
	 */
	private Integer curPage;
	
	/**
	 * ÿҳ��ʾ������Ĭ��10����
	 */
	private Integer pageCount=10;
	
	/**
	 *  ��һҳ:prePage=curPage==1?1:curPage-1;
	 */
	private Integer prePage;
	
	/**
	 * ��һҳ:nextPage=curPage==totalPage?totalPage:curPage+1;
	 */
	private Integer nextPage;
	 
	/**
	 * ��ҳ��:totalPage=totalCount%pageCount==0?totalCount/pageCount:totalCount/pageCount+1;
	 */
	private Integer totalPage;
	
	/**
	 * �ܼ�¼��������ݿ⣩
	 */
	private Integer total;
	
	/**
	 * ��ʼ����λ�ã� startIndex=(curPage-1)*pageCount+1;
	 */
	private Integer startIndex;
	
	/**
	 * ��������λ��:endIndex=curPage*pageCount;
	 */
	private Integer endIndex;
	
	/**
	 * �����洢�鵽�����
	 */
	private List rows;

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}


	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * ����
	 * @param args
	 */
	public static void main(String[] args) {
		int curPage = 2;
		int totalCount = 26;
		int pageCount = 5;
		PageTools pt = new PageTools(curPage, totalCount, pageCount);
		//��ȡ��һҳ
		System.out.println(pt.getPrePage());
		//��ȡ��һҳ
		System.out.println(pt.getNextPage());
		//��ȡ��ҳ��
		System.out.println(pt.getTotalPage());
		//��ȡ��ʼҳ
		System.out.println(pt.getStartIndex());
		//��ȡ����ҳ
		System.out.println(pt.getEndIndex());
	}
	
}
