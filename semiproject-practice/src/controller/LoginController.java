package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberVO;

public class LoginController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println(id+password);
		MemberVO mvo = MemberDAO.getInstance().login(id, password);
		System.out.println(mvo);
		if (mvo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("mvo", mvo);			
		}
		System.out.println(mvo.toString());
		return "redirect:member/login_result.jsp";
	}
}
