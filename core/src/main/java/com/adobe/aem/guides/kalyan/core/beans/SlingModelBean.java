package com.adobe.aem.guides.kalyan.core.beans;

import javax.inject.Inject;

import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Via;

public class SlingModelBean {
public String zipcode;
public String presentaddress;
public String fullname;
public String District;
public String bookname;
public String booksubject;
public String editondate;
public String bookediton;
public String collagename;
public String department;
public String gender;
public String year;

public String getYear() {
	return year;
}

public void setYear(String year) {
	this.year = year;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public String getCollagename() {
	return collagename;
}

public void setCollagename(String collagename) {
	this.collagename = collagename;
}

public String getBookediton() {
	return bookediton;
}

public void setBookediton(String bookediton) {
	this.bookediton = bookediton;
}

public String getEditondate() {
	return editondate;
}

public void setEditondate(String editondate) {
	this.editondate = editondate;
}

public String getBooksubject() {
	return booksubject;
}

public void setBooksubject(String booksubject) {
	this.booksubject = booksubject;
}

public String getBookname() {
	return bookname;
}

public void setBookname(String bookname) {
	this.bookname = bookname;
}

public String getDistrict(){
	return District;
}

public void setDistrict(String district) {
	this.District = district;
}

public String getPresentaddress() {
	return presentaddress;
}

public void setPresentaddress(String presentaddress) {
	this.presentaddress = presentaddress;
}


public String getFullname() {
	return fullname;
}

public void setFullname(String fullname) {
	this.fullname = fullname;
}

public String getZipcode() {
	return zipcode;
}

public void setZipcode(String zipcode) {
	this.zipcode = zipcode;
}
}
