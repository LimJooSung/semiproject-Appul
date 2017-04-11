package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.board.BoardVO;
import model.board.ListVO;
import model.board.PagingBean;
import model.inst.board.InstBoardDAO;
import model.proposal.board.ProBoardDAO;
import model.qna.board.QnABoardDAO;

public class MainController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if (session==null || session.getAttribute("mvo")==null){
			return "redirect:login.jsp";
		}
		String pageNo = request.getParameter("pageNo");
		InstBoardDAO instDao = InstBoardDAO.getInstance();
		ProBoardDAO proDao = ProBoardDAO.getInstance();
		QnABoardDAO qnaDao = QnABoardDAO.getInstance();
		int instTotalContent = instDao.getTotalContent();
		int proTotalContent = proDao.getTotalContentCount();
		int qnaTotalContent = qnaDao.getTotalContentCount();
		PagingBean instPagingBean = null;
		PagingBean proPagingBean = null;
		PagingBean qnaPagingBean = null;
		if (pageNo == null) {
			instPagingBean = new PagingBean(instTotalContent);
			proPagingBean = new PagingBean(proTotalContent);
			qnaPagingBean = new PagingBean(qnaTotalContent);
		} else {
			instPagingBean = new PagingBean(instTotalContent, Integer.parseInt(pageNo));	// PagingBean 생성(pageNo, total)
			proPagingBean = new PagingBean(proTotalContent, Integer.parseInt(pageNo));
			qnaPagingBean = new PagingBean(qnaTotalContent, Integer.parseInt(pageNo));
		}
		ArrayList<BoardVO> proList = ProBoardDAO.getInstance().getPostingList(proPagingBean);	// 건의사항 게시글 리스트
		ArrayList<BoardVO> qnaList = QnABoardDAO.getInstance().getPostingList(qnaPagingBean);	// QnA 게시글 리스트
		ArrayList<BoardVO> instList = InstBoardDAO.getInstance().getInstPostingList(instPagingBean);	// 강사게시글 리스트
		ListVO pvo = new ListVO(proList, proPagingBean);
		ListVO qvo = new ListVO(qnaList, qnaPagingBean);
		ListVO ivo = new ListVO(instList, instPagingBean);
		request.setAttribute("ivo", ivo);
		request.setAttribute("pvo", pvo);
		request.setAttribute("qvo", qvo);
		return "main.jsp";
	}
}
