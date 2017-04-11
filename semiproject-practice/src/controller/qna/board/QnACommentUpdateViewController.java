package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.qna.board.QnACommentDAO;
import model.qna.board.QnACommentVO;

public class QnACommentUpdateViewController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:login.jsp";
		}
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		QnACommentVO cvo = QnACommentDAO.getInstance().getQnAPostingCommentByNo(commentNo);		
		request.setAttribute( "cvo", cvo);
		return "/board/qna_comment_update.jsp";
	}
}
