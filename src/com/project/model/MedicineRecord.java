package com.project.model;

public class MedicineRecord {
	
	private int recordId;
	private String memberName;
	private String timeStamp;
	private String medicine;
	private String memberCondition;
	
	public MedicineRecord(String memberName, String medicine, String memberCondition) {
		super();
		this.memberName = memberName;
//		this.timeStamp = timeStamp;
		this.medicine = medicine;
		this.memberCondition = memberCondition;
	}
	
	public MedicineRecord(int recordId, String memberName, String timeStamp, String medicine, String memberCondition) {
		super();
		this.recordId = recordId;
		this.memberName = memberName;
		this.timeStamp = timeStamp;
		this.medicine = medicine;
		this.memberCondition = memberCondition;
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

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getMemberCondition() {
		return memberCondition;
	}

	public void setMemberCondition(String memberCondition) {
		this.memberCondition = memberCondition;
	}
	
	
}
