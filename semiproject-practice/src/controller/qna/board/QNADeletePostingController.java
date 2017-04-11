package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.qna.board.QnABoardDAO;

public class QNADeletePostingController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("boardNo"));
		QnABoardDAO.getInstance().deletePosting(no);
		return "redirect:DispatcherServlet?command=QNAboardlist&pageNo=1";
	}
}
