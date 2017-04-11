package controller.proposal.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.proposal.board.ProBoardDAO;

public class ProUpdatePostingViewController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			int boardNo=Integer.parseInt(request.getParameter("boardNo"));
			BoardVO vo = ProBoardDAO.getInstance().getProPostingByNo(boardNo);		
			request.setAttribute( "bvo", vo);
			return "board/pro_update.jsp";
	}

}
