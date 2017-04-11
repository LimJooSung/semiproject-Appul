package controller.inst.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.inst.board.InstBoardDAO;

public class InstDownloadController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		// 조회수를 증가시킨다.
		InstBoardDAO.getInstance().updateHit(boardNo);
		// 개별 게시물 조회
		BoardVO vo = InstBoardDAO.getInstance().getInstPostingByNo(boardNo);
		request.setAttribute("bvo", vo);
		String savePath = "C:\\java-kosta\\jquery-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0";
		String attachedFile = vo.getAttachedFile();

		attachedFile = new String(attachedFile.getBytes("EUC-kr"), "ISO8859_1");

		response.setContentType("application/octet-stream;charset=euc-kr");
		OutputStream os = response.getOutputStream();

		File file = new File(savePath, attachedFile);

		// 헤더에 파일이름 용량을 설정 "filename=" + URLEncoder.encode(filename, "UTF-8")
		response.setHeader("Content-Disposition", "attachment;attachedFile=" + file.getName() + ";");
		response.setHeader("Content-Length", "" + file.length());

		FileInputStream fis = new FileInputStream(file);

		int readCount = 0;
		byte[] bytestream = new byte[(int) file.length()];

		while ((readCount = fis.read(bytestream)) != -1) {
			os.write(bytestream, 0, readCount);
		}
		fis.close();
		os.close();

		return "AjaxView";
	}
}