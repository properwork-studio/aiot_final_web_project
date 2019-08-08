package com.project.model;

public class MedicineRecord {
	
	private int recordId;
	private String memberName;
	private String timeStamp;
	private String medicineCondition;
	
	public MedicineRecord(String memberName, String medicineCondition) {
		super();
		this.memberName = memberName;
//		this.timeStamp = timeStamp;
		this.medicineCondition = medicineCondition;
	}
	
	public MedicineRecord(int recordId, String memberName, String timeStamp, String medicineCondition) {
		super();
		this.recordId = recordId;
		this.memberName = memberName;
		this.timeStamp = timeStamp;
		this.medicineCondition = medicineCondition;
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

	public String getMedicineCondition() {
		return medicineCondition;
	}

	public void setMedicineCondition(String medicineCondition) {
		this.medicineCondition = medicineCondition;
	}
}
