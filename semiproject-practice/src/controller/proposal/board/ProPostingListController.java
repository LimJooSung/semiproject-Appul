package controller.proposal.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.board.ListVO;
import model.proposal.board.ProBoardDAO;

public class ProPostingListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int totalCount=ProBoardDAO.getInstance().getTotalContentCount();
		String pno=request.getParameter("pageNo");
		model.board.PagingBean pagingBean=null;
		if(pno==null){
			pagingBean=new model.board.PagingBean(totalCount);
		}else{
			pagingBean=new model.board.PagingBean(totalCount,Integer.parseInt(pno));
		}
		ArrayList<BoardVO> list=ProBoardDAO.getInstance().getPostingList(pagingBean);
		ListVO listVO=new ListVO(list,pagingBean);
		request.setAttribute("lvo", listVO);
		return "board/pro_list.jsp";

	}

}
