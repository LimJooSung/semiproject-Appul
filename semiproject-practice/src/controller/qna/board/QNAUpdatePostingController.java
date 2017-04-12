package controller.qna.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import model.qna.board.QnABoardDAO;
import model.qna.board.QnABoardVO;

public class QNAUpdatePostingController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:main.jsp";
		}
		MultipartRequest multi = null;
		int sizeLimit = 10 * 1024 * 1024; // 10메가입니다.
		String savePath = "C:\\java-kosta\\jquery-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0";
		try {
			multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String attachedFile = multi.getFilesystemName("attachedFile");
		int no = Integer.parseInt(multi.getParameter("boardNo"));
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String secret = multi.getParameter("secret");
		QnABoardVO vo = new QnABoardVO();
		vo.setBoardNo(no);
		vo.setTitle(title);
		vo.setAttachedFile(attachedFile);
		vo.setContent(content);
		vo.setSecret(secret);
		QnABoardDAO.getInstance().updatePosting(vo);
		String path = "redirect:DispatcherServlet?command=qnashowContentNotHit&boardNo=" + vo.getBoardNo();
		return path;
	}

}
