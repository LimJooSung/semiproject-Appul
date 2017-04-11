package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;


public class DeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");

		MemberDAO.getInstance().delete(id);
		HttpSession session=request.getSession(false);
		if(session!=null)
			session.invalidate();		
		return "redirect:member/delete_result.jsp";
	}
}
