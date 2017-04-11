package controller.inst.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.inst.board.InstCommentDAO;
import model.inst.board.InstCommentVO;

public class InstCommentUpdateViewController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:login.jsp";
		}
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		InstCommentVO cvo = InstCommentDAO.getInstance().getInstPostingCommentByNo(commentNo);		
		request.setAttribute( "cvo", cvo);
		return "/board/inst_comment_update.jsp";
	}
}
