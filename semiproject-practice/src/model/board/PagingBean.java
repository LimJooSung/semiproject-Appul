package model.board;
/**
 * 페이징 처리를 위한 비즈니스 클래스<br>
 * PagingBean method 구현 순서 <br>
 * getStartRowNumber()<br>
 * getEndRowNumber()<br>
 * 
 * getTotalPage()<br>
 * getTotalPageGroup()<br>
 * getNowPageGroup()<br>
 * 
 * getStartPageOfPageGroup()<br>	-------------- jsp에 접근
 * getEndPageOfPageGroup()<br>
 * isPreviousPageGroup()<br>
 * isNextPageGroup()<br>
 *
 */
public class PagingBean {
	/**
	 * 현재 페이지
	 */
	private int nowPage = 1;
	/**
	 * 페이지 당 보여줄 게시물 수
	 */
	private int contentNumberPerPage = 10;
	/**
	 * 페이지 그룹 당 페이지 수
	 */
	private int pageNumberPerPageGroup = 5;
	/**
	 * 현재 db에 저장된 총 게시물 수
	 */
	private int totalContents;

	public PagingBean() { }

	/**
	 * 총게시물수를 할당
	 * 
	 * @param totalContents
	 */
	public PagingBean(int totalContents) {
		this.totalContents = totalContents;
	}

	/**
	 * 총게시물수와 현재 페이지 정보를 할당
	 * 
	 * @param totalContents
	 * @param nowPage
	 */
	public PagingBean(int totalContents, int nowPage) {
		this.totalContents = totalContents;
		this.nowPage = nowPage;
	}

	/**
	 * 현재 페이지 리턴
	 * 
	 * @return
	 */
	public int getNowPage() {
		return nowPage;
	}

	/**
	 * 현 페이지에서 보여줄 행(row) 의 시작번호 이전페이지(현페이지-1) * contentNumberPerPage + 1
	 * 
	 * @return
	 */
	public int getStartRowNumber() {
		int startRowNumber = ((nowPage - 1) * contentNumberPerPage) + 1;
		return startRowNumber;
	}

	/**
	 * 현 페이지에서 보여줄 행(row)의 마지막 번호 현페이지*contentNumberPerPage 만약
	 * 총게시물수(totalContents) 보다 endRowNumber가 크다면 총게시물수(totalContents)가 마지막
	 * 번호(endRowNumber)가 된다
	 * 
	 * @return
	 */
	public int getEndRowNumber() {
		int endRowNumber = nowPage * contentNumberPerPage;
		if (endRowNumber > totalContents) {
			endRowNumber = totalContents;
		}
		return endRowNumber;
	}

	/**
	 * 총 페이지 수를 return한다.<br>
	 * 1. 전체 데이터(게시물) % 한 페이지에 보여줄 데이터 개수 <br>
	 * => 0 이면 둘을 / 값이 총 페이지 수<br>
	 * 2. 전체 데이터(게시물) % 한 페이지에 보여줄 데이터 개수 <br>
	 * => 0보다 크면 둘을 / 값에 +1을 한 값이 총 페이지 수<br>
	 * 게시물수 1 2 3 4 5 6 7 8 9 10 11 12<br>
	 * 1페이지 1~5<br>
	 * 2페이지 6~10<br>
	 * 3페이지 11 <br>
	 * ex) 게시물 32 개 , 페이지당 게시물수 5개-> 7 페이지
	 * 
	 * @return
	 */
	public int getTotalPage() {
		int totalPage = 0;
		totalPage = totalContents / contentNumberPerPage;
		if (totalContents % contentNumberPerPage != 0)
			totalPage++;
		return totalPage;
	}

	/**
	 * 총 페이지 그룹의 수를 return한다.<br>
	 * 1. 총 페이지수 % Page Group 내 Page 수. <br>
	 * => 0 이면 둘을 / 값이 총 페이지 수<br>
	 * 2. 총 페이지수 % Page Group 내 Page 수. <br>
	 * => 0보다 크면 둘을 / 값에 +1을 한 값이 총 페이지 수<br>
	 * ex) 총 게시물 수 23 개 <br>
	 * 총 페이지 ? 총 페이지 그룹수 ? <br>
	 * 페이지 1 2 3 4 5<br>
	 * 페이지그룹 1234(1그룹) 5(2그룹)<br>
	 * 
	 */
	public int getTotalPageGroup() {
		int totalPageGroup = 0;
		totalPageGroup = getTotalPage() / pageNumberPerPageGroup;
		if (getTotalPage() / pageNumberPerPageGroup != 0)
			totalPageGroup++;
		return totalPageGroup;
	}

	/**
	 * 현재 페이지가 속한 페이지 그룹 번호(몇 번째 페이지 그룹인지) 을 return 하는 메소드 <br>
	 * 1. 현재 페이지 % Page Group 내 Page 수 => 0 이면 <br>
	 * 둘을 / 값이 현재 페이지 그룹. <br>
	 * 2. 현재 페이지 % Page Group 내 Page 수 => 0 크면 <br>
	 * 둘을 / 값에 +1을 한 값이 현재 페이지 그룹<br>
	 * 페이지 1 2 3 4 /5 6 7 8/ 9 10 1그룹 2그룹 3그룹
	 * 
	 * @return
	 */
	public int getNowPageGroup() {
		int nowPageGroup = 0;
		nowPageGroup = getNowPage() / pageNumberPerPageGroup;
		if (getNowPage() % pageNumberPerPageGroup != 0) {
			nowPageGroup++;
		}
		return nowPageGroup;
	}

	/**
	 * 현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 return 한다.<br>
	 * Page Group 내 Page 수*(현재 페이지 그룹 -1) + 1을 한 값이 첫 페이지이다.<br>
	 * (페이지 그룹*페이지 그룹 개수, 그룹의 마지막 번호이므로) <br>
	 * 페이지 그룹 <br>
	 * 1 2 3 4 -> 5 6 7 8 -> 9 10 <br>
	 * 
	 * @return
	 */
	public int getStartPageOfPageGroup() {
		int num = 0;
		num = (pageNumberPerPageGroup * (getNowPageGroup() - 1)) + 1;
		return num;
	}

	/**
	 * 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 return 한다.<br>
	 * 1. 현재 페이지 그룹 * 페이지 그룹 개수 가 마지막 번호이다. <br>
	 * 2. 그 그룹의 마지막 페이지 번호가 전체 페이지의 마지막 페이지 번호보다 <br>
	 * 큰 경우는 전체 페이지의 마지막 번호를 return 한다.<br>
	 * 1 2 3 4 -> 5 6 7 8 -> 9 10
	 * 
	 * @return
	 */
	public int getEndPageOfPageGroup() {
		int num = 0;
		num = pageNumberPerPageGroup * getNowPageGroup();
		if (num > getTotalPage()) {
			num = getTotalPage();
		}
		return num;
	}

	/**
	 * 이전 페이지 그룹이 있는지 체크하는 메서드 <br>
	 * 현재 페이지가 속한 페이지 그룹이 1보다 크면 true<br>
	 * ex ) 페이지 1 2 3 4 / 5 6 7 8 / 9 10 <br>
	 * 1 2 3 group
	 * 
	 * @return
	 */
	public boolean isPreviousPageGroup() {
		boolean flag = false;
		if (getNowPageGroup() > 1) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 다음 페이지 그룹이 있는지 체크하는 메서드 <br>
	 * 현재 페이지 그룹이 마지막 페이지 그룹(<br>
	 * 마지막 페이지 그룹 == 총 페이지 그룹 수) 보다 작으면 true<br>
	 * * ex ) 페이지 <br>
	 * 1 2 3 4 / 5 6 7 8 / 9 10 <br>
	 * 1 2 3 group
	 * 
	 * @return
	 */
	public boolean isNextPageGroup() {
		boolean flag = false;
		if (getNowPageGroup() < getTotalPageGroup()) {
			flag = true;
		}
		return flag;
	}

	public static void main(String args[]) {
		PagingBean p = new PagingBean(47, 10);
		// 현페이지의 시작 row number 를 조회 46
		System.out.println("getBeginRowNumber:" + p.getStartRowNumber());
		// 현페이지의 마지막 row number 를 조회 47
		System.out.println("getEndRowNumber:" + p.getEndRowNumber());
		// 전체 페이지 수 : 10
		System.out.println("getTotalPage:" + p.getTotalPage());	
		// 전체 페이지 그룹 수 : 3
		System.out.println("getTotalPageGroup:" + p.getTotalPageGroup());
		System.out.println("////////////////////////////");
		p = new PagingBean(31, 6);// 게시물수 31 현재 페이지 6
		// 현페이지의 시작 row number 를 조회 26
		System.out.println("getStartRowNumber:" + p.getStartRowNumber());
		// 현페이지의 마지막 row number 를 조회 30
		System.out.println("getEndRowNumber:" + p.getEndRowNumber());
		// 게시물수 31 -> 총페이지수 7 -> 총페이지그룹->2
		// 현재 페이지 그룹 : 2
		System.out.println("getNowPageGroup:" + p.getNowPageGroup());
		// 페이지 그룹의 시작 페이지 : 5
		System.out.println("getStartPageOfPageGroup:" + p.getStartPageOfPageGroup());
		// 페이지 그룹의 마지막 페이지 : 7
		System.out.println("getEndPageOfPageGroup:" + p.getEndPageOfPageGroup());
		// 이전 페이지 그룹이 있는 지 : true
		System.out.println("isPreviousPageGroup:" + p.isPreviousPageGroup());	
		// 다음 페이지 그룹이 있는 지 : false
		System.out.println("isNextPageGroup:" + p.isNextPageGroup());
	}
}