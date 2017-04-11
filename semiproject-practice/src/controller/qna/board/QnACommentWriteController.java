package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.member.MemberVO;
import model.qna.board.QnACommentDAO;
import model.qna.board.QnACommentVO;

public class QnACommentWriteController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:login.jsp";
		}
		int boardNo = Integer.parseInt(request.getParameter("comment_board"));
		String id = request.getParameter("comment_id");
		String content = request.getParameter("comment_content");
		QnACommentVO vo = new QnACommentVO();
		vo.setBoardNo(boardNo);
		vo.setContent(id);
		vo.setContent(content);
		vo.setMember((MemberVO) session.getAttribute("mvo"));
		QnACommentDAO.getInstance().postingQnAComment(vo);
		String path = "redirect:DispatcherServlet?command=QnAShowContentNotHit&boardNo=" + vo.getBoardNo();
		return path;
	}
}
