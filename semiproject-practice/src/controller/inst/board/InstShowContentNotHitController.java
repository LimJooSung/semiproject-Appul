package controller.inst.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.inst.board.InstBoardDAO;
import model.inst.board.InstBoardVO;
import model.inst.board.InstCommentDAO;
import model.inst.board.InstCommentVO;

public class InstShowContentNotHitController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no=Integer.parseInt(request.getParameter("boardNo"));		
		// 개별 게시물 조회  
		InstBoardVO vo = (InstBoardVO) InstBoardDAO.getInstance().getInstPostingByNo(no);	
		request.setAttribute("bvo", vo);
		ArrayList<InstCommentVO> cvo =InstCommentDAO.getInstance().getInstPostingCommentList(vo.getBoardNo());
		request.setAttribute("cvo", cvo);
		return "board/inst_show_content.jsp";
	}
}
