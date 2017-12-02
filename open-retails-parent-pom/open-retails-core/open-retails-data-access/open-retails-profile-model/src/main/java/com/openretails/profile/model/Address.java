package com.openretails.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.openretails.profile.model.support.BaseEntity;
@Entity
@Table(name = "T_ADDRESS")
public class Address extends BaseEntity{

	private static final long serialVersionUID = 2779885384896272527L;

	@NotEmpty
	@Column(name = "ADDRESS_FREE_TEXT", nullable = false,length=255)
	private String addressFreeText;
	@NotNull
	@Column(name = "PIN_CODE", nullable = false)
	private Long pinCode;

	@NotEmpty
	@Column(name = "COUNTRY_NAME", nullable = false)
	private String countryName;
	@NotEmpty
	@Column(name = "COUNTRY_CODE", nullable = false)
	private String countryCode;
	@Column(name = "COMMENT", nullable = false)
	private String comment;
	@NotEmpty
	@Column(name = "CITY", nullable = false)
	private String city;
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
		final Address other = (Address) obj;
		if (addressFreeText == null) {
			if (other.addressFreeText != null) {
				return false;
			}
		} else if (!addressFreeText.equals(other.addressFreeText)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (comment == null) {
			if (other.comment != null) {
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			return false;
		}
		if (countryCode == null) {
			if (other.countryCode != null) {
				return false;
			}
		} else if (!countryCode.equals(other.countryCode)) {
			return false;
		}
		if (countryName == null) {
			if (other.countryName != null) {
				return false;
			}
		} else if (!countryName.equals(other.countryName)) {
			return false;
		}
		if (pinCode == null) {
			if (other.pinCode != null) {
				return false;
			}
		} else if (!pinCode.equals(other.pinCode)) {
			return false;
		}
		return true;
	}
	public String getAddressFreeText() {
		return addressFreeText;
	}

	public String getCity() {
		return city;
	}

	public String getComment() {
		return comment;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public Long getPinCode() {
		return pinCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (addressFreeText == null ? 0 : addressFreeText.hashCode());
		result = prime * result + (city == null ? 0 : city.hashCode());
		result = prime * result + (comment == null ? 0 : comment.hashCode());
		result = prime * result + (countryCode == null ? 0 : countryCode.hashCode());
		result = prime * result + (countryName == null ? 0 : countryName.hashCode());
		result = prime * result + (pinCode == null ? 0 : pinCode.hashCode());
		return result;
	}

	public void setAddressFreeText(String addressFreeText) {
		this.addressFreeText = addressFreeText;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

}
