package controller.inst.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.inst.board.InstBoardDAO;

public class InstUpdateViewController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		BoardVO vo = InstBoardDAO.getInstance().getInstPostingByNo(boardNo);		
		request.setAttribute( "bvo", vo);
		return "/board/inst_update.jsp";
	}
}
