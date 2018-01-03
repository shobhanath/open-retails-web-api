package com.openretails.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.openretails.profile.model.support.BaseEntity;
@Entity
@Table(name = "T_ADDRESS")
public class Address extends BaseEntity{

	private static final long serialVersionUID = 2779885384896272527L;

	@NotEmpty
	@NotNull
	@Column(name = "ADDRESS_FREE_TEXT", nullable = false,length=255)
	private String addressFreeText;

	@Column(name = "COMMENT", nullable = false)
	private String comment;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Address other = (Address) obj;
		if (addressFreeText == null) {
			if (other.addressFreeText != null) {
				return false;
			}
		} else if (!addressFreeText.equals(other.addressFreeText)) {
			return false;
		}
		if (comment == null) {
			if (other.comment != null) {
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			return false;
		}
		return true;
	}

	public String getAddressFreeText() {
		return addressFreeText;
	}

	public String getComment() {
		return comment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (addressFreeText == null ? 0 : addressFreeText.hashCode());
		result = prime * result + (comment == null ? 0 : comment.hashCode());
		return result;
	}

	public void setAddressFreeText(String addressFreeText) {
		this.addressFreeText = addressFreeText;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
