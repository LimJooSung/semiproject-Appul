package model.qna.board;

import model.member.MemberVO;
public class QnACommentVO {
	private int boardNo;
	private int commentNo;
	private String content;
	private String timePosted;
	private MemberVO member; // id가 외래키이므로
	public QnACommentVO() {
		super();
	}
	public QnACommentVO(int boardNo, int commentNo, String content, String timePosted, MemberVO member) {
		super();
		this.boardNo = boardNo;
		this.commentNo = commentNo;
		this.content = content;
		this.timePosted = timePosted;
		this.member = member;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "CommentVO [boardNo=" + boardNo + ", commentNo=" + commentNo + ", content=" + content + ", timePosted="
				+ timePosted + ", member=" + member + "]";
	}
	
	
}
