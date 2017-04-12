package controller.selectingpresent;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.selectingpresenter.SelectingPresenterDAO;

public class UpdateCntPresentationController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();		
		String id = request.getParameter("id");
		int cntPresentation = Integer.parseInt(request.getParameter("cntPresentation"));
		SelectingPresenterDAO.getInstance().updateCntPresentation(id, cntPresentation);
		out.close();
		return "AjaxView";
	}
}
