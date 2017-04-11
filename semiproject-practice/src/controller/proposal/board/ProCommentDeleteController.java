package controller.proposal.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.proposal.board.ProCommentDAO;

public class ProCommentDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if (session==null || session.getAttribute("mvo")==null){
			return "redirect:login.jsp";
		}
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		ProCommentDAO.getInstance().deletingProComment(commentNo);
		return "redirect:DispatcherServlet?command=proShowContentNotHit&boardNo=1";
	}
}
