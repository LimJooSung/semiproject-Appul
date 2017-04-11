package model.board;

import model.member.MemberVO;

public abstract class BoardVO {
	private int boardNo;
	private String title;
	private String content;
	private String timePosted;
	private int hits;
	private String attachedFile;	// 파일 경로를 저장
	private MemberVO member = new MemberVO();	// id가 외래키이므로
	
	public BoardVO() {
		super();
	}

	public BoardVO(int boardNo, String title, String content, String timePosted, int hits, String attachedFile,
			MemberVO member) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.timePosted = timePosted;
		this.hits = hits;
		this.attachedFile = attachedFile;
		this.member = member;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getAttachedFile() {
		return attachedFile;
	}
	public void setAttachedFile(String attachedFile) {
		this.attachedFile = attachedFile;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", timePosted="
				+ timePosted + ", hits=" + hits + ", attachedFile=" + attachedFile + ", member=" + member + "]";
	}
}
