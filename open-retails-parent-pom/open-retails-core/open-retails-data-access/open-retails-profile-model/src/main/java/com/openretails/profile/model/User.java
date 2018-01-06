package com.openretails.profile.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.openretails.profile.model.support.BaseEntity;
import com.openretails.profile.model.support.TableNames;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableNames.USERS,uniqueConstraints=@UniqueConstraint(columnNames={"PRIMARY_EMAIL_ID","USER_NAME"}))
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@Column(name = "FIRST_NAME", nullable = false, length = 255)
	private String firstName;

	@Column(name = "MIDDLE_NAME", nullable = false, length = 255)
	private String middleName;
	@Column(name = "LAST_NAME", nullable = false, length = 255)
	private String lastName;
	@Column(name = "IS_MOBILE_VERIFIED", nullable = false)
	private boolean isMobileVerified;
	@Column(name = "IS_EMAIL_VERIFIED", nullable = false, length = 255)
	private boolean isEmailVerified;
	@Column(name = "PRIMARY_EMAIL_ID", nullable = false, length = 255)
	private String primaryEmailId;
	@Column(name = "PRIMARY_MOBILE_NUMBER", nullable = false, length = 10)
	private Long primaryMobileNumber;
	@Column(name = "PASSWORD", nullable = false, length = 60)
	private String password;

	@Column(name = "COMMENT", nullable = false, length = 255)
	private String comment;
	@Column(name = "TITLE", nullable = false, length = 255)
	private String title;
	@Column(name = "USER_NAME", nullable = false, length = 255)
	private String username;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
	private UserType userType;

	@JoinColumn(name = "PERMANENT_ADDRESS_ID")
	@ManyToOne(cascade = { CascadeType.ALL })
	private Address permanentAddress;

	@JoinColumn(name = "SECONDARY_ADDRESS_ID")
	@ManyToOne(cascade = { CascadeType.ALL })
	private Address secondaryAddress;

	@Column(name = "AGE", nullable = false)
	private Integer age;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Gender gender;

	@Column(name = "SALARY", nullable = false)
	private double salary;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DOB", nullable = false, updatable = false)
	private Date birthDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = TableNames.USERS_ROLES, joinColumns = @JoinColumn(name = "USER_ID") , inverseJoinColumns = @JoinColumn(name = "ROLE_ID") )
	private Set<Role> roles;

	public User(User user) {
		super();
		firstName = user.getFirstName();
		middleName = user.getMiddleName();
		lastName = user.getLastName();
		isMobileVerified = user.isMobileVerified();
		isEmailVerified = user.isEmailVerified();
		primaryEmailId = user.getPrimaryEmailId();
		primaryMobileNumber = user.getPrimaryMobileNumber();
		password = user.getPassword();
		comment = user.getComment();
		title = user.getTitle();
		username = user.getUsername();
		userType = user.getUserType();
		permanentAddress = user.getPermanentAddress();
		secondaryAddress = user.getSecondaryAddress();
		age = user.getAge();
		gender = user.getGender();
		salary = user.getSalary();
		roles = user.getRoles();
	}


}
