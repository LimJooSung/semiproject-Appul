package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class QNADeleteCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		//BoardDAO.getInstance().deletePosting(no);
		return "redirect:DispatcherServlet?command=boardlist&boardno=1";
	}
}
