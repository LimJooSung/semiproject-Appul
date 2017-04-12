package model.selectingpresenter;

import model.member.MemberVO;

public class SelectingPresenterVO{
	private MemberVO memberVO;
	private int cntPresentation;
	private int memNumber;
	
	public SelectingPresenterVO() {
		super();
	}

	public SelectingPresenterVO(MemberVO memberVO, int cntPresentation) {
		super();
		this.memberVO = memberVO;
		this.cntPresentation = cntPresentation;
	}

	public SelectingPresenterVO(MemberVO memberVO, int cntPresentation, int memNumber) {
		super();
		this.memberVO = memberVO;
		this.cntPresentation = cntPresentation;
		this.memNumber = memNumber;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public int getCntPresentation() {
		return cntPresentation;
	}

	public void setCntPresentation(int cntPresentation) {
		this.cntPresentation = cntPresentation;
	}

	public int getMemNumber() {
		return memNumber;
	}

	public void setMemNumber(int memNumber) {
		this.memNumber = memNumber;
	}

	@Override
	public String toString() {
		return "SelectingPresenterVO [memberVO=" + memberVO + ", cntPresentation=" + cntPresentation + "]";
	}
}























