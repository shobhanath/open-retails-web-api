package com.openretails.data;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openretails.data.validation.UserValidationMessages;

import lombok.Data;

@Data
public abstract class BaseDTO implements Serializable {
	private static final long serialVersionUID = 5537554836633470687L;

	public interface Existing {
    }

    public interface New {
    }
    
	@NotNull(message = UserValidationMessages.VALIDATE_IDENTITY,groups = Existing.class)
    @Null(message = UserValidationMessages.VALIDATE_IDENTITY_NULL,groups = New.class)
	private Long identity;
	
	@NotNull(message = UserValidationMessages.VALIDATE_OBSOLETE_NOT_NULL,groups = {New.class,Existing.class})
	@AssertTrue(message = UserValidationMessages.VALIDATE_OBSOLETE,groups = {New.class})
	private boolean obsolete;
	
	private String createdByUser;
	
	private String modifiedByUser;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Date modifiedDate;

}
