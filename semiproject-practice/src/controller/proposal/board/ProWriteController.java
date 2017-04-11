package controller.proposal.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.board.BoardVO;
import model.member.MemberVO;
import model.proposal.board.ProBoardDAO;
import model.proposal.board.ProBoardVO;

public class ProWriteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      HttpSession session=request.getSession(false);
	      if (session==null || session.getAttribute("mvo")==null) {
	         return "redirect:login.jsp";
	      }
	      String title = request.getParameter("title");
	      String content = request.getParameter("content");
	            
	      BoardVO bvo=new ProBoardVO();
	      bvo.setTitle(title);
	      bvo.setContent(content);
	      bvo.setMember((MemberVO)session.getAttribute("mvo"));      
	   
	      ProBoardDAO.getInstance().posting(bvo);
	      String path="redirect:DispatcherServlet?command=proShowContentNotHit&boardNo="+bvo.getBoardNo();
	      return path;
	}
}
