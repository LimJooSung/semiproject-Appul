package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class QNAUpdateCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("update controller Ok");
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String content =request.getParameter("content");
/*		HttpSession session=request.getSession();
		if(session.getAttribute("membervo")!=null){
			BoardVO vo1 = BoardDAO.getInstance().boardUpdate(no,title,content);
			request.setAttribute("vo", vo1);
		}*/
		return "redirect:main.jsp";
	}
}
