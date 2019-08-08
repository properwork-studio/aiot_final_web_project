package com.project.model;

public class Member {
	
	private int memberId;
	private String memberName;
	private String memberCode;
	private String birthday;
	private String idNumber;
	private String photoPath;
	
	public Member(String memberName, String memberCode, String birthday, String idNumber, String photoPath) {
		super();
		this.memberName = memberName;
		this.memberCode = memberCode;
		this.birthday = birthday;
		this.idNumber = idNumber;
		this.photoPath = photoPath;
	}
	
	public Member(int memberId, String memberName, String memberCode, String birthday, String idNumber, String photoPath) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberCode = memberCode;
		this.birthday = birthday;
		this.idNumber = idNumber;
		this.photoPath = photoPath;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getMemberCode() {
		return memberCode;
	}
	
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
}
