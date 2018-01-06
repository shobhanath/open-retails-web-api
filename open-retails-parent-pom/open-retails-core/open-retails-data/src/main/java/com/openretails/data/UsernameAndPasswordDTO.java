package com.openretails.data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.openretails.data.validation.UserValidationMessages;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsernameAndPasswordDTO implements Serializable {
	private static final long serialVersionUID = 8049242054559854037L;
	@NotBlank(message = UserValidationMessages.VALIDATE_USERNAME)
	@Size(message = UserValidationMessages.VALIDATE_USERNAME_SIZE,min=6, max=20)
	private String username;
	@NotBlank(message = UserValidationMessages.VALIDATE_PASSWORD)
	@Size(message = UserValidationMessages.VALIDATE_PASSWORD_SIZE,min=6, max=20)
	private String password;
}
