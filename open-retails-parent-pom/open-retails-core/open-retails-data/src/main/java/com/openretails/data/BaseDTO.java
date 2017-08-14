package com.openretails.data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public abstract class BaseDTO implements Serializable {

	private static final long serialVersionUID = 5537554836633470687L;

	private Long identity;

	private boolean obsolete;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Date createdDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Date modifiedDate;

	private String createdByUser;
	private String modifiedByUser;

	public String getCreatedByUser() {
		return createdByUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Long getIdentity() {
		return identity;
	}

	public String getModifiedByUser() {
		return modifiedByUser;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public boolean isObsolete() {
		return obsolete;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setIdentity(Long identity) {
		this.identity = identity;
	}

	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
	}

}
