package controller.inst.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.inst.board.InstBoardDAO;

public class InstDeletePostingController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if (session==null || session.getAttribute("mvo")==null){
			return "redirect:login.jsp";
		}
		String boardNo = request.getParameter("boardNo");
		InstBoardDAO.getInstance().deleteInstPosting(Integer.parseInt(boardNo));
		// 게시물 목록을 보여주기 위해
		// path를 DispatcherServlet?command=list setting하고
		// 리다이렉트 방식으로 이동시킨다. 
		return "redirect:DispatcherServlet?command=instList";
	}
}
