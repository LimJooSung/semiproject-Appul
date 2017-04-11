package model.proposal.board;

import model.board.BoardVO;
import model.member.MemberVO;

public class ProBoardVO extends BoardVO {
	private boolean hidden;

	public ProBoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProBoardVO(int boardNo, String title, String content, String timePosted, int hits, String attachedFile,
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
		return super.toString() + "ProBoardVO [hidden=" + hidden + "]";
	}
}
