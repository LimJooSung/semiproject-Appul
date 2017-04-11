package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.qna.board.QnABoardDAO;

public class QNAShowContentNoHitController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("boardNo"));
		BoardVO vo = QnABoardDAO.getInstance().getQnAPostingByNo(no);	
		request.setAttribute("bvo", vo);
		return "/board/qna_show_content.jsp";
	}
	
}
