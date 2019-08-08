package com.project.model;

public class DoorRecord {

	private int recordId;
	private String memberName;
	private String timeStamp;
	private String condition;
	private String realFake;
	
	public DoorRecord(String memberName, String timeStamp, String condition, String realFake) {
		super();
		this.memberName = memberName;
		this.timeStamp = timeStamp;
		this.condition = condition;
		this.realFake = realFake;
	}
	
	public DoorRecord(int recordId, String memberName, String timeStamp, String condition, String realFake) {
		super();
		this.recordId = recordId;
		this.memberName = memberName;
		this.timeStamp = timeStamp;
		this.condition = condition;
		this.realFake = realFake;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getRealFake() {
		return realFake;
	}

	public void setRealFake(String realFake) {
		this.realFake = realFake;
	}
	
	
}
