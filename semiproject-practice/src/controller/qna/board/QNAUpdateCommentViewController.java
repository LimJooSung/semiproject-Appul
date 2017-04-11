package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class QNAUpdateCommentViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("update controller Ok");
		String no = request.getParameter("no");
/*		BoardVO vo = BoardDAO.getInstance().findDetailByNo(no);
		request.setAttribute("vo", vo);*/
		return "/board/update.jsp";
	}
}
