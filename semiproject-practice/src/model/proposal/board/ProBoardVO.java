package model.proposal.board;

import model.board.BoardVO;
import model.member.MemberVO;

public class ProBoardVO extends BoardVO {
	private String secret;

	public ProBoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProBoardVO(int boardNo, String title, String content, String timePosted, int hits, String attachedFile,
			MemberVO member) {
		super(boardNo, title, content, timePosted, hits, attachedFile, member);
	}

	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	@Override
	public String toString() {
		return super.toString() + "ProBoardVO [secret=" + secret + "]";
	}
}
