package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;

public class MemberIdCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		//System.out.println(id);
		
		boolean flag=MemberDAO.getInstance().idcheck(id);

		//out.println(flag);
		if(flag){
			out.print("ok");
		}else{
			out.print("fail");
		}
		out.close();
		return "AjaxView";
	}

}
