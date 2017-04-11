package controller.proposal.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.board.ListVO;
import model.board.PagingBean;
import model.proposal.board.ProBoardDAO;

public class ProSearchController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String searchTxt = request.getParameter("searchTxt");
		String pageNo = request.getParameter("pageNo");
		ProBoardDAO dao = ProBoardDAO.getInstance();
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
			list = dao.getSearchedProPostingListByTitle(pagingBean, searchTxt);
		} else if (type.equals("titleAndContent")) {
			list = dao.getSearchedProPostingListByTitleAndContent(pagingBean, searchTxt);
		} else if (type.equals("writer")) {
			list = dao.getSearchedProPostingListByWriter(pagingBean, searchTxt);
		}
		ListVO vo = new ListVO(list, pagingBean);
		System.out.println(vo);
		request.setAttribute("lvo", vo);
		request.setAttribute("type", type);
		request.setAttribute("searchTxt", searchTxt);
		return "board/pro_searched_board.jsp";
	}

}
