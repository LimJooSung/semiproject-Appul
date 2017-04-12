package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberVO;

public class FindIdController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String dateOfBirth=request.getParameter("dateOfBirth");		

		MemberVO mvo=MemberDAO.getInstance().findid(name, dateOfBirth);
		if(mvo!=null){
			HttpSession session=request.getSession();
			session.setAttribute("mvo", mvo);			
		}
		return "redirect:member/findid_result.jsp";	
		
		
	}
}
