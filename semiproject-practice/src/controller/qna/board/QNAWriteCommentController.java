package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class QNAWriteCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		/*BoardVO vo = new BoardVO(title,id,content);
		int tempNo = BoardDAO.getInstance().Write(vo);
		return "redirect:DispatcherServlet?command=hitsupdate&no=" + tempNo;
		*/
		return null;
	}
}
