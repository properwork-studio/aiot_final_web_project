package com.project.model;

public class DoorRecord {

	private int recordId;
	private String memberName;
	private String timeStamp;
	private String condition;
	
	public DoorRecord(String memberName, String timeStamp, String condition) {
		super();
		this.memberName = memberName;
		this.timeStamp = timeStamp;
		this.condition = condition;
	}
	
	public DoorRecord(int recordId, String memberName, String timeStamp, String condition) {
		super();
		this.recordId = recordId;
		this.memberName = memberName;
		this.timeStamp = timeStamp;
		this.condition = condition;
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
}
