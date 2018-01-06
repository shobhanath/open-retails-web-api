package com.openretails.data;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.openretails.data.validation.AddressValidationMessages;
import com.openretails.data.validation.UserValidationMessages;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName(value = "user")
@Data
@EqualsAndHashCode(callSuper=true)
public class UserDTO extends BaseDTO {

	private static final long serialVersionUID = -3578663668010710336L;

	@NotBlank(message = UserValidationMessages.VALIDATE_FIRST_NAME,groups = {New.class,Existing.class})
	@Size(message = UserValidationMessages.VALIDATE_FIRST_NAME_SIZE,min=2, max=255,groups = {New.class,Existing.class})
	private String firstName;
	@Size(message = UserValidationMessages.VALIDATE_MIDDLE_NAME_SIZE,min=2, max=255,groups = {New.class,Existing.class})
	private String middleName;
	@Size(message = UserValidationMessages.VALIDATE_LAST_NAME_SIZE,min=2, max=255,groups = {New.class,Existing.class})
	private String lastName;
	private boolean isMobileVerified;
	private boolean isEmailVerified;
	@NotBlank(message = UserValidationMessages.VALIDATE_PRIMARY_EMIL_ADDRESS,groups = {New.class,Existing.class})
	@Email(message=UserValidationMessages.VALIDATE_PRIMARY_EMIL_ADDRESS,groups = {New.class,Existing.class})
	private String primaryEmailId;
	@Min(value = 10, message = UserValidationMessages.VALIDATE_PRIMARY_MOBILE_NUMBER,groups = {New.class,Existing.class})
    @Max(value = 10, message = UserValidationMessages.VALIDATE_PRIMARY_MOBILE_NUMBER,groups = {New.class,Existing.class})
	private Long primaryMobileNumber;
	@NotNull(message = UserValidationMessages.VALIDATE_USER_TYPE,groups = {New.class,Existing.class})
	private UserType userType;
	@NotBlank(message = UserValidationMessages.VALIDATE_PASSWORD,groups = New.class)
	@Size(message = UserValidationMessages.VALIDATE_PASSWORD_SIZE,min=6, max=20,groups = New.class)
	@Pattern(regexp=UserValidationMessages.REG_EXPRESSION_PASSWORD,groups=New.class,message=UserValidationMessages.VALIDATE_PASSWORD_POLICY)
	private String password;
	private String comment;
	private String title;
	@NotBlank(message = UserValidationMessages.VALIDATE_USERNAME,groups= {New.class,Existing.class})
	@Size(message = UserValidationMessages.VALIDATE_USERNAME_SIZE,min=6, max=20,groups = {New.class,Existing.class})
	private String username;
	@NotNull(message = AddressValidationMessages.VALIDATE_PRIMARY_ADDRESS,groups = {New.class,Existing.class})
	private AddressDTO permanentAddress;
	private AddressDTO secondaryAddress;
	@Min(value = 18, message = UserValidationMessages.VALIDATE_AGE,groups = {New.class,Existing.class})
    @Max(value = 60, message = UserValidationMessages.VALIDATE_AGE,groups = {New.class,Existing.class})
	private Integer age;
	@NotNull(message = UserValidationMessages.VALIDATE_GENDER,groups = {New.class,Existing.class})
	private Gender gender;
	@Past(groups= {New.class,Existing.class})
	private Date birthDate;
	
	private double salary;
}
