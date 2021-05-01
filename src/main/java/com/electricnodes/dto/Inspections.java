
/* Copyright 2021 freecodeformat.com */
package com.electricnodes.dto;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inspections")
public class Inspections {

	@org.springframework.data.annotation.Id
	private ObjectId id;
	private String businessName;
	private String date;
	private String result;
	private String sector;
	private Address address;

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getId() {
		return id;
	}

	

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSector() {
		return sector;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

}