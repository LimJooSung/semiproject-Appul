package controller.proposal.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.proposal.board.ProCommentDAO;
import model.proposal.board.ProCommentVO;

public class ProCommentUpdateController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String content = request.getParameter("content");
		ProCommentVO cvo = new ProCommentVO();
		cvo.setCommentNo(commentNo);
		cvo.setContent(content);
		ProCommentDAO.getInstance().updatePostingComment(cvo);
		String path = "redirect:DispatcherServlet?command=proShowContentNotHit&boardNo=1";
		return path;
	}
}







