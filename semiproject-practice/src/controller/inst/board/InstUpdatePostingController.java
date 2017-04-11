package controller.inst.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.inst.board.InstBoardDAO;
import model.inst.board.InstBoardVO;

public class InstUpdatePostingController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		InstBoardVO vo = new InstBoardVO();
		vo.setBoardNo(boardNo);
		vo.setTitle(title);
		vo.setContent(content);
		InstBoardDAO.getInstance().updatePosting(vo);			
		String path = "redirect:DispatcherServlet?command=instShowContentNotHit&boardNo=" + vo.getBoardNo();
		return path;
	}
}







