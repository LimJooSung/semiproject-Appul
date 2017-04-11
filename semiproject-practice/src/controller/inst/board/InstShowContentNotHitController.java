package controller.inst.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.inst.board.InstBoardDAO;
import model.inst.board.InstBoardVO;

public class InstShowContentNotHitController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no=Integer.parseInt(request.getParameter("boardNo"));		
		// 개별 게시물 조회  
		InstBoardVO vo = (InstBoardVO) InstBoardDAO.getInstance().getInstPostingByNo(no);	
		request.setAttribute("bvo", vo);
		return "board/inst_show_content.jsp";
	}
}







