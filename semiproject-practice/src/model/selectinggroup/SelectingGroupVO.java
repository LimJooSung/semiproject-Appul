	package model.selectinggroup;

import model.member.MemberVO;

public class SelectingGroupVO {
	private MemberVO memberVO;
	private int groupNo;
	private int selectingGroupCount;

	public SelectingGroupVO() {
		super();
	}

	public SelectingGroupVO(MemberVO memberVO, int groupNo, int selectingGroupCount) {
		super();
		this.memberVO = memberVO;
		this.groupNo = groupNo;
		this.selectingGroupCount = selectingGroupCount;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getSelectingGroupCount() {
		return selectingGroupCount;
	}

	public void setSelectingGroupCount(int selectingGroupCount) {
		this.selectingGroupCount = selectingGroupCount;
	}

	@Override
	public String toString() {
		return "SelectingGroupVO [memberVO=" + memberVO + ", groupNo=" + groupNo + ", selectingGroupCount="
				+ selectingGroupCount + "]";
	}
}
