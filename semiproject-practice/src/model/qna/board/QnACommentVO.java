package model.qna.board;

import model.member.MemberVO;

public class QnACommentVO {
	private int boardNo;
	private int commentNo;
	private MemberVO member = new MemberVO();
	private String timePosted;
	private int parent;
	private String content;
	public QnACommentVO() {
		super();
	}
	public QnACommentVO(int boardNo, int commentNo, MemberVO member, String timePosted, int parent, String content) {
		super();
		this.boardNo = boardNo;
		this.commentNo = commentNo;
		this.member = member;
		this.timePosted = timePosted;
		this.parent = parent;
		this.content = content;
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
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	public String getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CommentVO [boardNo=" + boardNo + ", commentNo=" + commentNo + ", member=" + member + ", timePosted="
				+ timePosted + ", parent=" + parent + ", content=" + content + "]";
	}
}
