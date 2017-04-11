package controller.inst.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.inst.board.InstCommentDAO;
import model.inst.board.InstCommentVO;
import model.member.MemberVO;

public class InstCommentWriteController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:login.jsp";
		}
		int boardNo = Integer.parseInt(request.getParameter("comment_board"));
		String id = request.getParameter("comment_id");
		String content = request.getParameter("comment_content");
		InstCommentVO vo = new InstCommentVO();
		vo.setBoardNo(boardNo);
		vo.setContent(id);
		vo.setContent(content);
		vo.setMember((MemberVO) session.getAttribute("mvo"));
		InstCommentDAO.getInstance().postingInstComment(vo);
		String path = "redirect:DispatcherServlet?command=instShowContentNotHit&boardNo=" + vo.getBoardNo();
		return path;
	}
}
