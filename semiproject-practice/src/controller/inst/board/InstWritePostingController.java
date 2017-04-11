package controller.inst.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import model.inst.board.InstBoardDAO;
import model.inst.board.InstBoardVO;
import model.member.MemberVO;

public class InstWritePostingController implements Controller {
	@Override //attachedFile
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:login.jsp";
		}
		MultipartRequest multi = null;
		int sizeLimit = 10 * 1024 * 1024; // 10메가입니다.
		//String savePath = request.getSession().getServletContext().getRealPath("/upload"); // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준
		String savePath = "C:\\java-kosta\\jquery-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0";
		// C:\java-kosta\semi-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0
		try {
			multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String attachedFile = multi.getFilesystemName("attachedFile");
		String title = multi.getParameter("title"); 
		String content = multi.getParameter("content"); 
		InstBoardVO vo = new InstBoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setAttachedFile(attachedFile);
		vo.setMember((MemberVO) session.getAttribute("mvo"));
		InstBoardDAO.getInstance().postingInstBoard(vo);
		String path = "redirect:DispatcherServlet?command=instShowContentNotHit&boardNo=" + vo.getBoardNo();
		return path;
	}
}
