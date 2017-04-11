package model.inst.board;

import model.board.BoardVO;
import model.member.MemberVO;

public class InstBoardVO extends BoardVO {
	private double avgRating;
	
	public InstBoardVO() {
		super();
	}
	public InstBoardVO(int boardNo, String title, String content, String timePosted, int hits, String attachedFile, MemberVO member) {
		super(boardNo, title, content, timePosted, hits, attachedFile, member);
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public double getAvgRating() {
		return avgRating;
	}
	@Override
	public String toString() {
		return super.toString() + "InstBoardVO [avgRating=" + avgRating + "]";
	}
}
