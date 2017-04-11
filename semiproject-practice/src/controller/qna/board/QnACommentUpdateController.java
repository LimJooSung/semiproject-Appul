package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.qna.board.QnACommentDAO;
import model.qna.board.QnACommentVO;

public class QnACommentUpdateController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String content = request.getParameter("content");
		QnACommentVO cvo = new QnACommentVO();
		cvo.setCommentNo(commentNo);
		cvo.setContent(content);
		QnACommentDAO.getInstance().updatePostingComment(cvo);
		String path = "redirect:DispatcherServlet?command=qnaShowContentNotHit&boardNo=1";
		return path;
	}
}







