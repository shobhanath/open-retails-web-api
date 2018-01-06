package com.openretails.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.openretails.data.validation.UserValidationMessages;

import lombok.Data;

@Data
public class EmailAddress {
	@NotBlank(message = UserValidationMessages.VALIDATE_PRIMARY_EMIL_ADDRESS)
	@Email(message=UserValidationMessages.VALIDATE_PRIMARY_EMIL_ADDRESS)
	private String email;
}
