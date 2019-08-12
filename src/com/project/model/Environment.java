package com.project.model;

public class Environment {

	private int record_id;
	private String timeStamp;
	private String temperature;
	private String humidity;
	private String co;
	
	public Environment(String temperature, String humidity, String co) {
		super();
		this.temperature = temperature;
		this.humidity = humidity;
		this.co = co;
	}
	
	public Environment(String timeStamp, String temperature, String humidity, String co) {
		super();
		this.timeStamp = timeStamp;
		this.temperature = temperature;
		this.humidity = humidity;
		this.co = co;
	}
	
	public Environment(int record_id, String timeStamp, String temperature, String humidity, String co) {
		super();
		this.record_id = record_id;
		this.timeStamp = timeStamp;
		this.temperature = temperature;
		this.humidity = humidity;
		this.co = co;
	}

	public int getRecord_id() {
		return record_id;
	}

	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}
	
}
