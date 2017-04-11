package controller.proposal.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.proposal.board.ProBoardDAO;
import model.proposal.board.ProBoardVO;

public class ProUpdatePostingController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:index.jsp";
		}
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String secret=request.getParameter("secret");
		ProBoardVO vo=new ProBoardVO();
		vo.setBoardNo(boardNo);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setSecret(secret);
		ProBoardDAO.getInstance().updatePosting(vo);
		String path="redirect:DispatcherServlet?command=proShowContentNotHit&boardNo="+vo.getBoardNo();
		return path;
	}

}
