package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberVO;

public class RegisterController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		String dateOfBirth=request.getParameter("dateOfBirth");
		String memberType=request.getParameter("memberType");	
		
		MemberVO mvo=new MemberVO();	
		mvo.setId(id);
		mvo.setPassword(password);
		mvo.setName(name);
		mvo.setGender(gender);
		mvo.setDateOfBirth(dateOfBirth);
		mvo.setMemberType(memberType);		
		MemberDAO.getInstance().register(mvo);	
		if(mvo!=null){
			HttpSession session=request.getSession();
			session.setAttribute("mvo", mvo);			
		}
		return "redirect:member/register_result.jsp";
	}
	}









