package controller.inst.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.board.ListVO;
import model.board.PagingBean;
import model.inst.board.InstBoardDAO;

public class InstPostingListController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo = request.getParameter("pageNo");
		InstBoardDAO dao = InstBoardDAO.getInstance();
		int totalContent = dao.getTotalContent();
		PagingBean pagingBean = null;
		if (pageNo == null) {
			pagingBean = new PagingBean(totalContent);
		} else {
			pagingBean = new PagingBean(totalContent, Integer.parseInt(pageNo));	// PagingBean 생성(pageNo, total)
		}
		ArrayList<BoardVO> list = InstBoardDAO.getInstance().getInstPostingList(pagingBean);
		ListVO vo = new ListVO(list, pagingBean);
		request.setAttribute("lvo", vo);
		return "board/inst_board.jsp";
	}
}
