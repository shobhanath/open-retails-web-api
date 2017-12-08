package com.openretails.profile.model;

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
	@Column(name = "PRIMARY_MOBILE_NUMBER", nullable = false, length = 10)
	private Long primaryMobileNumber;
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

	@Enumerated(EnumType.ORDINAL)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = TableNames.USER_ROLE, joinColumns = @JoinColumn(name = "USER_ID") , inverseJoinColumns = @JoinColumn(name = "ROLE_ID") )
	private Set<Role> roles;

	public User() {
		super();
	}

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (age == null) {
			if (other.age != null) {
				return false;
			}
		} else if (!age.equals(other.age)) {
			return false;
		}
		if (comment == null) {
			if (other.comment != null) {
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (gender != other.gender) {
			return false;
		}
		if (isEmailVerified != other.isEmailVerified) {
			return false;
		}
		if (isMobileVerified != other.isMobileVerified) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (middleName == null) {
			if (other.middleName != null) {
				return false;
			}
		} else if (!middleName.equals(other.middleName)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (permanentAddress == null) {
			if (other.permanentAddress != null) {
				return false;
			}
		} else if (!permanentAddress.equals(other.permanentAddress)) {
			return false;
		}
		if (primaryEmailId == null) {
			if (other.primaryEmailId != null) {
				return false;
			}
		} else if (!primaryEmailId.equals(other.primaryEmailId)) {
			return false;
		}
		if (primaryMobileNumber == null) {
			if (other.primaryMobileNumber != null) {
				return false;
			}
		} else if (!primaryMobileNumber.equals(other.primaryMobileNumber)) {
			return false;
		}
		if (roles == null) {
			if (other.roles != null) {
				return false;
			}
		} else if (!roles.equals(other.roles)) {
			return false;
		}
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary)) {
			return false;
		}
		if (secondaryAddress == null) {
			if (other.secondaryAddress != null) {
				return false;
			}
		} else if (!secondaryAddress.equals(other.secondaryAddress)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (userType == null) {
			if (other.userType != null) {
				return false;
			}
		} else if (!userType.equals(other.userType)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public double getSalary() {
		return salary;
	}

	public Address getSecondaryAddress() {
		return secondaryAddress;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (age == null ? 0 : age.hashCode());
		result = prime * result + (comment == null ? 0 : comment.hashCode());
		result = prime * result + (firstName == null ? 0 : firstName.hashCode());
		result = prime * result + (gender == null ? 0 : gender.hashCode());
		result = prime * result + (isEmailVerified ? 1231 : 1237);
		result = prime * result + (isMobileVerified ? 1231 : 1237);
		result = prime * result + (lastName == null ? 0 : lastName.hashCode());
		result = prime * result + (middleName == null ? 0 : middleName.hashCode());
		result = prime * result + (password == null ? 0 : password.hashCode());
		result = prime * result + (permanentAddress == null ? 0 : permanentAddress.hashCode());
		result = prime * result + (primaryEmailId == null ? 0 : primaryEmailId.hashCode());
		result = prime * result + (primaryMobileNumber == null ? 0 : primaryMobileNumber.hashCode());
		result = prime * result + (roles == null ? 0 : roles.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ temp >>> 32);
		result = prime * result + (secondaryAddress == null ? 0 : secondaryAddress.hashCode());
		result = prime * result + (title == null ? 0 : title.hashCode());
		result = prime * result + (userType == null ? 0 : userType.hashCode());
		result = prime * result + (username == null ? 0 : username.hashCode());
		return result;
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

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}


	public void setSecondaryAddress(Address secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
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
