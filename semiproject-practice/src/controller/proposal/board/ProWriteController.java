package controller.proposal.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import model.member.MemberVO;
import model.proposal.board.ProBoardDAO;
import model.proposal.board.ProBoardVO;

public class ProWriteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      HttpSession session=request.getSession(false);
	      if (session==null || session.getAttribute("mvo")==null) {
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
	      String secret =multi.getParameter("secret");    
	      ProBoardVO bvo=new ProBoardVO();
	      bvo.setTitle(title);
	      bvo.setContent(content);
	      bvo.setSecret(secret);
	      bvo.setMember((MemberVO)session.getAttribute("mvo"));      
	      bvo.setAttachedFile(attachedFile);
	      ProBoardDAO.getInstance().posting(bvo);
	      String path="redirect:DispatcherServlet?command=proShowContentNotHit&boardNo="+bvo.getBoardNo();
	      return path;
	}
}
