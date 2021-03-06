package com.openretails.data;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.openretails.data.validation.UserValidationMessages;

import lombok.Data;

@Data
public abstract class BaseDTO implements Serializable {
	public interface Existing {
    }

	public interface New {
    }

    private static final long serialVersionUID = 5537554836633470687L;
    
	@NotNull(message = UserValidationMessages.VALIDATE_IDENTITY,groups = Existing.class)
    @Null(message = UserValidationMessages.VALIDATE_IDENTITY_NULL,groups = New.class)
	@Min(value = 1, message = UserValidationMessages.VALIDATE_IDENTITY,groups = {Existing.class})
	private Long identity;
	
	@AssertTrue(message = UserValidationMessages.VALIDATE_OBSOLETE,groups = {New.class})
	private boolean obsolete;
	
	private String createdByUser;
	
	private String modifiedByUser;
	
	private Date createdDate;

	private Date modifiedDate;

}
