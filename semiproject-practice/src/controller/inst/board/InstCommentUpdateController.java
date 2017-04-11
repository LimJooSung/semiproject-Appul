package controller.inst.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.inst.board.InstCommentDAO;
import model.inst.board.InstCommentVO;

public class InstCommentUpdateController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String content = request.getParameter("content");
		InstCommentVO cvo = new InstCommentVO();
		cvo.setCommentNo(commentNo);
		cvo.setContent(content);
		InstCommentDAO.getInstance().updatePostingComment(cvo);
		String path = "redirect:DispatcherServlet?command=instShowContentNotHit&boardNo=1";
		return path;
	}
}







