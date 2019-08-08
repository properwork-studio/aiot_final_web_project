package com.project.model;

public class Medicine {

	private int ruleId;
	private String memberName;
	private String alertTime;
	private String box_1;
	private String box_2;
	private String box_3;
	private String box_4;
	private String box_5;
	private String medicine_1;
	private String medicine_2;
	private String medicine_3;
	private String medicine_4;
	private String medicine_5;
	
	public Medicine(String memberName, String alertTime, String box_1, String box_2, String box_3,
			String box_4, String box_5, String medicine_1, String medicine_2, String medicine_3, String medicine_4,
			String medicine_5) {
		super();
		this.memberName = memberName;
		this.alertTime = alertTime;
		this.box_1 = box_1;
		this.box_2 = box_2;
		this.box_3 = box_3;
		this.box_4 = box_4;
		this.box_5 = box_5;
		this.medicine_1 = medicine_1;
		this.medicine_2 = medicine_2;
		this.medicine_3 = medicine_3;
		this.medicine_4 = medicine_4;
		this.medicine_5 = medicine_5;
	}
	
	public Medicine(int ruleId, String memberName, String alertTime, String box_1, String box_2, String box_3,
			String box_4, String box_5, String medicine_1, String medicine_2, String medicine_3, String medicine_4,
			String medicine_5) {
		super();
		this.ruleId = ruleId;
		this.memberName = memberName;
		this.alertTime = alertTime;
		this.box_1 = box_1;
		this.box_2 = box_2;
		this.box_3 = box_3;
		this.box_4 = box_4;
		this.box_5 = box_5;
		this.medicine_1 = medicine_1;
		this.medicine_2 = medicine_2;
		this.medicine_3 = medicine_3;
		this.medicine_4 = medicine_4;
		this.medicine_5 = medicine_5;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}

	public String getBox_1() {
		return box_1;
	}

	public void setBox_1(String box_1) {
		this.box_1 = box_1;
	}

	public String getBox_2() {
		return box_2;
	}

	public void setBox_2(String box_2) {
		this.box_2 = box_2;
	}

	public String getBox_3() {
		return box_3;
	}

	public void setBox_3(String box_3) {
		this.box_3 = box_3;
	}

	public String getBox_4() {
		return box_4;
	}

	public void setBox_4(String box_4) {
		this.box_4 = box_4;
	}

	public String getBox_5() {
		return box_5;
	}

	public void setBox_5(String box_5) {
		this.box_5 = box_5;
	}

	public String getMedicine_1() {
		return medicine_1;
	}

	public void setMedicine_1(String medicine_1) {
		this.medicine_1 = medicine_1;
	}

	public String getMedicine_2() {
		return medicine_2;
	}

	public void setMedicine_2(String medicine_2) {
		this.medicine_2 = medicine_2;
	}

	public String getMedicine_3() {
		return medicine_3;
	}

	public void setMedicine_3(String medicine_3) {
		this.medicine_3 = medicine_3;
	}

	public String getMedicine_4() {
		return medicine_4;
	}

	public void setMedicine_4(String medicine_4) {
		this.medicine_4 = medicine_4;
	}

	public String getMedicine_5() {
		return medicine_5;
	}

	public void setMedicine_5(String medicine_5) {
		this.medicine_5 = medicine_5;
	}
}
