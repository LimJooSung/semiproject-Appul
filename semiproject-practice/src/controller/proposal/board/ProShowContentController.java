package controller.proposal.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.board.BoardVO;
import model.proposal.board.ProBoardDAO;
import model.proposal.board.ProCommentDAO;
import model.proposal.board.ProCommentVO;

public class ProShowContentController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:index.jsp";
		}
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		//조회수를 증가시킨다. 
		ProBoardDAO.getInstance().updateHit(boardNo);
		// 개별 게시물 조회  
		BoardVO vo = ProBoardDAO.getInstance().getProPostingByNo(boardNo);	
		request.setAttribute("bvo", vo);
		ArrayList<ProCommentVO> cvo =ProCommentDAO.getInstance().getProPostingCommentList(vo.getBoardNo());
		request.setAttribute("cvo", cvo);
		return "board/pro_show_content.jsp";
	}

}
