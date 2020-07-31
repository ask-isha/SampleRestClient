package com.restclient;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class Emp {

	private long eID;
	private String eName;
	private String dptName;
	private Date createdDate;

	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emp(long eID, String eName, String dptName) {
		super();
		this.eID = eID;
		this.eName = eName;
		this.dptName = dptName;
	}
	public long geteID() {
		return eID;
	}
	public void seteID(long eID) {
		this.eID = eID;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getDptName() {
		return dptName;
	}
	public void setDptName(String dptName) {
		this.dptName = dptName;
	}
	
	@JsonSerialize(using=DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
		
	
}
