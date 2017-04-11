package model.qna.board;

import model.board.BoardVO;
import model.member.MemberVO;

public class QnABoardVO extends BoardVO {
	private boolean hidden;

	public QnABoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnABoardVO(int boardNo, String title, String content, String timePosted, int hits, String attachedFile,
			MemberVO member) {
		super(boardNo, title, content, timePosted, hits, attachedFile, member);
	}

	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	@Override
	public String toString() {
		return super.toString() + "QnABoardVO [hidden=" + hidden + "]";
	}
}
