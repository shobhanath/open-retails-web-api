package com.openretails.data;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "user")
public class UserDTO extends BaseDTO {

	private static final long serialVersionUID = -3578663668010710336L;

	private String firstName;
	private String middleName;
	private String lastName;
	private boolean isMobileVerified;
	private boolean isEmailVerified;
	private String primaryEmailId;
	private String secondaryEmailId;
	private Long primaryMobileNumber;
	private Long secondaryMobileNumber;
	private long userTypeId;

	private String password;
	private String comment;
	private String title;
	private String username;

	private String locale;

	private AddressDTO permanentAddress;

	private AddressDTO secondaryAddress;

	private Integer age;

	private Gender gender;

	private double salary;

	public Integer getAge() {
		return age;
	}

	public String getComment() {
		return comment;
	}

	public String getFirstName() {
		return firstName;
	}

	public Gender getGender() {
		return gender;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLocale() {
		return locale;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getPassword() {
		return password;
	}

	public AddressDTO getPermanentAddress() {
		return permanentAddress;
	}

	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	public Long getPrimaryMobileNumber() {
		return primaryMobileNumber;
	}

	public double getSalary() {
		return salary;
	}

	public AddressDTO getSecondaryAddress() {
		return secondaryAddress;
	}

	public String getSecondaryEmailId() {
		return secondaryEmailId;
	}

	public Long getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getUsername() {
		return username;
	}

	public long getUserTypeId() {
		return userTypeId;
	}

	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	public boolean isMobileVerified() {
		return isMobileVerified;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setMobileVerified(boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPermanentAddress(AddressDTO permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	public void setPrimaryMobileNumber(Long primaryMobileNumber) {
		this.primaryMobileNumber = primaryMobileNumber;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setSecondaryAddress(AddressDTO secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}

	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
	}

	public void setSecondaryMobileNumber(Long secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserTypeId(long userTypeId) {
		this.userTypeId = userTypeId;
	}

}
