package com.openretails.data;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@JsonRootName(value = "user")
@Data
public class UserDTO extends BaseDTO {

	private static final long serialVersionUID = -3578663668010710336L;

	private String firstName;
	private String middleName;
	private String lastName;
	private boolean isMobileVerified;
	private boolean isEmailVerified;
	private String primaryEmailId;
	private Long primaryMobileNumber;
	private UserType userType;
	private String password;
	private String comment;
	private String title;
	private String username;
	private AddressDTO permanentAddress;
	private AddressDTO secondaryAddress;
	private Integer age;
	private Gender gender;
	private double salary;

}
