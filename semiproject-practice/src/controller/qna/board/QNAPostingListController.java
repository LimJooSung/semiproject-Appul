package controller.qna.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.board.ListVO;
import model.board.PagingBean;
import model.qna.board.QnABoardDAO;

/*
 * PagingBean(pageNo,total)-> getlist(pagingbean)호출
 * 
 * ListVO -> list,pagingbean두개 있는 클래스
 */

public class QNAPostingListController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int totalCount = QnABoardDAO.getInstance().getTotalContentCount();
		String pno = request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if (pno == null) {
			pagingBean = new PagingBean(totalCount);
		} else {
			pagingBean = new PagingBean(totalCount, Integer.parseInt(pno));
		}
		ArrayList<BoardVO> list = QnABoardDAO.getInstance().getPostingList(pagingBean);
		ListVO listVO = new ListVO(list, pagingBean);
		request.setAttribute("lvo", listVO);
		return "board/qna_board.jsp";
	}
}
