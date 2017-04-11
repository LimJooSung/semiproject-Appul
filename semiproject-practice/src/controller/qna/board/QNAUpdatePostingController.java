package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.qna.board.QnABoardDAO;

public class QNAUpdatePostingController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:main.jsp";
		}*/
		int no = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardVO vo = new BoardVO();
		vo.setBoardNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		QnABoardDAO.getInstance().updatePosting(vo);
		String path = "redirect:DispatcherServlet?command=QNAshowContentNotHit&boardNo=" + vo.getBoardNo();
		return path;
	}

}
