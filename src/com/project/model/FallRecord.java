package com.project.model;

public class FallRecord {
	
	private int recordId;
	private String timeStamp;
	private String condition;
	
	public FallRecord(String timeStamp, String condition) {
		super();
		this.timeStamp = timeStamp;
		this.condition = condition;
	}
	
	public FallRecord(int recordId, String timeStamp, String condition) {
		super();
		this.recordId = recordId;
		this.timeStamp = timeStamp;
		this.condition = condition;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
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
