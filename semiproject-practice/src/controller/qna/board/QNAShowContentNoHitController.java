package controller.qna.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.qna.board.QnABoardDAO;
import model.qna.board.QnACommentDAO;
import model.qna.board.QnACommentVO;

public class QNAShowContentNoHitController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("boardNo"));
		BoardVO vo = QnABoardDAO.getInstance().getQnAPostingByNo(no);	
		request.setAttribute("bvo", vo);
		ArrayList<QnACommentVO> cvo =QnACommentDAO.getInstance().getQnAPostingCommentList(vo.getBoardNo());
		request.setAttribute("cvo", cvo);
		return "/board/qna_show_content.jsp";
	}
	
}
