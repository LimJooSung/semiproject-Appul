package controller.proposal.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.proposal.board.ProBoardDAO;
import model.proposal.board.ProCommentDAO;
import model.proposal.board.ProCommentVO;

public class ProShowContentNoHitController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));		
		// 개별 게시물 조회  
		BoardVO vo = ProBoardDAO.getInstance().getProPostingByNo(boardNo);	
		request.setAttribute("bvo", vo);
		ArrayList<ProCommentVO> cvo =ProCommentDAO.getInstance().getProPostingCommentList(vo.getBoardNo());
		request.setAttribute("cvo", cvo);
		return "board/pro_show_content.jsp";
	}

}
