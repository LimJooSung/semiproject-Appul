package controller.selectingpresent;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.Controller;
import model.selectingpresenter.SelectingPresenterDAO;
import model.selectingpresenter.SelectingPresenterService;
import model.selectingpresenter.SelectingPresenterVO;

public class SelectingPresenterController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		ArrayList<SelectingPresenterVO> presenterList = SelectingPresenterDAO.getInstance().getPresenterList();
		ArrayList<SelectingPresenterVO> allMemberList = SelectingPresenterDAO.getInstance().getAllPresenterList();
		SelectingPresenterVO presenter = SelectingPresenterService.getInstance().selectingPresenter(presenterList);
		
		JSONObject obj = new JSONObject();
		obj.put("id", presenter.getMemberVO().getId());
		obj.put("name", presenter.getMemberVO().getName());
		obj.put("cntPresentation", presenter.getCntPresentation());
		obj.put("memNumber", presenter.getMemNumber());
		obj.put("allMemberList", allMemberList);
		out.print(obj);
		out.close();
		return "AjaxView";
	}
}