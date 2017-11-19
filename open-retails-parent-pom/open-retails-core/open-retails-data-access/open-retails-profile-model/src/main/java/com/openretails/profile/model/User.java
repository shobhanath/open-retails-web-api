package com.openretails.profile.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.openretails.profile.model.support.BaseEntity;
import com.openretails.profile.model.support.TableNames;

@Entity
@Table(name = TableNames.USER)
public class User extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@NotEmpty
	@Column(name = "FIRST_NAME", nullable = false, length = 255)
	private String firstName;
	@Column(name = "MIDDLE_NAME", nullable = false, length = 255)
	private String middleName;
	@Column(name = "LAST_NAME", nullable = false, length = 255)
	@NotEmpty
	private String lastName;
	@Column(name = "IS_MOBILE_VERIFIED", nullable = false)
	private boolean isMobileVerified;
	@Column(name = "IS_EMAIL_VERIFIED", nullable = false, length = 255)
	private boolean isEmailVerified;
	@NotEmpty
	@Column(name = "PRIMARY_EMAIL_ID", nullable = false, length = 255)
	private String primaryEmailId;
	@Column(name = "SECONDARY_EMAIL_ID", nullable = false, length = 255)
	private String secondaryEmailId;
	@Column(name = "PRIMARY_MOBILE_NUMBER", nullable = false, length = 10)
	private Long primaryMobileNumber;
	@Column(name = "SECONDARY_MOBILE_NUMBER", nullable = false, length = 10)
	private Long secondaryMobileNumber;

	@NotEmpty
	@Column(name = "PASSWORD", nullable = false, length = 60)
	private String password;
	@Column(name = "COMMENT", nullable = false, length = 255)
	private String comment;
	@Column(name = "TITLE", nullable = false, length = 255)
	private String title;
	@NotEmpty
	@Column(name = "USER_NAME", nullable = false, length = 255)
	private String username;

	@Column(name = "LOCALE", nullable = false, length = 255)
	private String locale;

	@ManyToOne
	@JoinColumn(name = "USER_TYPE_ID")
	private UserType userType;

	@JoinColumn(name = "PERMANENT_ADDRESS_ID")
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Address permanentAddress;

	@JoinColumn(name = "SECONDARY_ADDRESS_ID")
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Address secondaryAddress;

	@Column(name = "AGE", nullable = false)
	private Integer age;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "SALARY", nullable = false)
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

	public Address getPermanentAddress() {
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

	public Address getSecondaryAddress() {
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

	public UserType getUserType() {
		return userType;
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

	public void setPermanentAddress(Address permanentAddress) {
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

	public void setSecondaryAddress(Address secondaryAddress) {
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

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
