package controller.inst.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.board.ListVO;
import model.board.PagingBean;
import model.inst.board.InstBoardDAO;

public class InstSearchController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String searchTxt = request.getParameter("searchTxt");
		String pageNo = request.getParameter("pageNo");
		InstBoardDAO dao = InstBoardDAO.getInstance();
		int totalSearchedContent = dao.getTotalSearchedContent(type, searchTxt);
		PagingBean pagingBean = null;
		if (pageNo == null) {
			pagingBean = new PagingBean(totalSearchedContent);
		} else {
			pagingBean = new PagingBean(totalSearchedContent, Integer.parseInt(pageNo));	// PagingBean 생성(pageNo, total)
		}
		// 검색 조건에 따른 검색
		ArrayList<BoardVO> list = null;
		if (type.equals("title")) {
			list = dao.getSearchedInstPostingListByTitle(pagingBean, searchTxt);
		} else if (type.equals("titleAndContent")) {
			list = dao.getSearchedInstPostingListByTitleAndContent(pagingBean, searchTxt);
		} else if (type.equals("writer")) {
			list = dao.getSearchedInstPostingListByWriter(pagingBean, searchTxt);
		}
		ListVO vo = new ListVO(list, pagingBean);
		request.setAttribute("lvo", vo);
		request.setAttribute("type", type);
		request.setAttribute("searchTxt", searchTxt);
		return "board/inst_searched_board.jsp";
	}
}
