package com.project.model;

public class Contact {
	
	private int contactId;
	private String contactName;
	private String relationship;
	private String phoneNumber;
	private String email;
	
	public Contact(String contactName, String relationship, String phoneNumber, String email) {
		super();
		this.contactName = contactName;
		this.relationship = relationship;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public Contact(int contactId, String contactName, String relationship, String phoneNumber, String email) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.relationship = relationship;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
