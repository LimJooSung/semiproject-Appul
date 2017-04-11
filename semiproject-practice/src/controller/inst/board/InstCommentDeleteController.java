package controller.inst.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.inst.board.InstCommentDAO;

public class InstCommentDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if (session==null || session.getAttribute("mvo")==null){
			return "redirect:login.jsp";
		}
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		InstCommentDAO.getInstance().deletingInstComment(commentNo);
		return "redirect:DispatcherServlet?command=instShowContentNotHit&boardNo=1";
	}
}
