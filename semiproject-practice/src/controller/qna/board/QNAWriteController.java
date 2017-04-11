package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.board.BoardVO;
import model.member.MemberVO;
import model.qna.board.QnABoardDAO;
import model.qna.board.QnABoardVO;

public class QNAWriteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:main.jsp";
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
				
		BoardVO bvo=new QnABoardVO();
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setMember((MemberVO)session.getAttribute("mvo"));		
	
		QnABoardDAO.getInstance().posting(bvo);
		String path="redirect:DispatcherServlet?command=QNAshowContentNotHit&boardNo="+bvo.getBoardNo();
		return path;
	}
}
