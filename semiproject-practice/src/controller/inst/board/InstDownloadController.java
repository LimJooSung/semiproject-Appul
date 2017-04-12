package controller.inst.board;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.board.BoardVO;
import model.inst.board.InstBoardDAO;

public class InstDownloadController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		BoardVO vo = InstBoardDAO.getInstance().getInstPostingByNo(boardNo);	
		request.setAttribute("bvo", vo);
		String savePath = "C:\\java-kosta\\jquery-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0";
		String fileName = vo.getAttachedFile(); 
		String docName = URLEncoder.encode(fileName,"UTF-8"); // UTF-8로 인코딩
		docName = docName.replaceAll("\\+", "%20");	// 공백부분이 +로 표기되는것 처리
		
		 // 파일 다운로드  
		try {
			File file = new File(savePath, fileName);
			if (!file.exists()) { // 파일 존재 확인
				throw new Exception();
			}

			// 파일명 변환
			response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
			response.setHeader("Content-Type", "application/octet-stream");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			int read;
			byte readByte[] = new byte[(int) file.length()];
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
			OutputStream outs = response.getOutputStream();
			while ((read = fin.read(readByte, 0, (int) file.length())) != -1) {
				outs.write(readByte, 0, read);
			}
			outs.flush();
			outs.close();
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "AjaxView";
	}
}
