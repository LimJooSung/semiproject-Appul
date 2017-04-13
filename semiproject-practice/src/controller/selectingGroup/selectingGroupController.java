package controller.selectingGroup;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.Controller;
import model.member.MemberDAO;
import model.member.MemberVO;
import model.selectinggroup.SelectingGroupService;

public class selectingGroupController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<MemberVO> memberList = MemberDAO.getInstance().getMemberList("all");
		
		SelectingGroupService service = new SelectingGroupService();
		int group[][] = service.prepare();
		String[][] _group = new String[6][6];
		
		System.out.println("*********************************************");
		
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 6; j++)
				System.out.print(group[i][j] + ", ");
			System.out.println();
		}
		System.out.println("*********************************************");
		
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				_group[i][j] = memberList.get(group[i][j] - 1).getName();
				//System.out.print(_group[i][j] + ", ");
			}
			//System.out.println();
		}
		
		PrintWriter out=response.getWriter();
		HashMap<String,String[]> map = new HashMap<String,String[]>();
		JSONObject json = null;
		
		map.put("no1", _group[0]);
		map.put("no2", _group[1]);
		map.put("no3", _group[2]);
		map.put("no4", _group[3]);
		map.put("no5", _group[4]);
		map.put("no6", _group[5]);
		
		json=new JSONObject(map);			
		out.print(json.toString());		
		out.close();
			
		return "AjaxView";
	}

}
