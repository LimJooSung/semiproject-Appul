package model.board;

import java.util.ArrayList;

public class ListVO {
	private ArrayList<BoardVO> list;	// 와일드 카드
	private PagingBean pagingBean;
	public ListVO(ArrayList<BoardVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public ArrayList<BoardVO> getList() {
		return list;
	}
	public void setList(ArrayList<BoardVO> list) {
		this.list = list;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "ListVO [list=" + list + ", pagingBean=" + pagingBean + "]";
	}
}
// list와 pagingBean을 가짐

//얘를 뷰에서 처리