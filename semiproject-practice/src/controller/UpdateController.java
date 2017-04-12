package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberVO;

public class UpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		String dateOfBirth=request.getParameter("dateOfBirth");		
		String memberType=request.getParameter("memberType");
		System.out.println(dateOfBirth);

		MemberVO vo=new MemberVO(id,password,name,gender,dateOfBirth,memberType);
		MemberDAO.getInstance().update(vo);
		HttpSession session=request.getSession(false);
		if(session!=null)
			session.setAttribute("mvo", vo);		
		return "redirect:member/update_result.jsp";	
	}
}
