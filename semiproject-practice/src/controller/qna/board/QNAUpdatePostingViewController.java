package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.qna.board.QnABoardDAO;

public class QNAUpdatePostingViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	/*	HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:main.jsp";
		}*/
		int no=Integer.parseInt(request.getParameter("boardNo"));
		BoardVO vo = QnABoardDAO.getInstance().getPostingByNo(no);		
		request.setAttribute( "bvo", vo);
		return "/board/qna_update.jsp";
	}
}
