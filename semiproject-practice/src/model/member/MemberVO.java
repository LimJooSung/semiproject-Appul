package model.member;

import java.util.HashSet;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String gender;
	private String dateOfBirth;
	private String memberType;
	private int memberNumber;
	private HashSet<Integer> previousMemberNumberSet = new HashSet<Integer>();

	/*
	 * memberType 유효값 일반학생 우수학생 강사
	 */
	public MemberVO() {
		super();
	}

	public MemberVO(String memberName) {
		super();
		this.name = memberName;
	}

	public MemberVO(String name, String dateOfBirth) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public MemberVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public MemberVO(String id, String password, String name, String dateOfBirth) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public MemberVO(int memberNumber, String id, String name, String memberType, String gender) {
		super();
		this.memberNumber = memberNumber;
		this.id = id;
		this.name = name;
		this.memberType = memberType;
		this.gender = gender;
	}

	public MemberVO(String id, String password, String name, String gender, String dateOfBirth, String memberType) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.memberType = memberType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public HashSet<Integer> getPreviousMemberNumberSet() {
		return previousMemberNumberSet;
	}

	public void addPreviousMemberNumberSet(int[] previousMemberNumberArr) {

		for (int i = 0; i < previousMemberNumberArr.length; i++) {
			if (getMemberNumber() != previousMemberNumberArr[i]) {
				this.previousMemberNumberSet.add(previousMemberNumberArr[i]);
			}

		}

	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", memberType=" + memberType + "]";

	}

}
