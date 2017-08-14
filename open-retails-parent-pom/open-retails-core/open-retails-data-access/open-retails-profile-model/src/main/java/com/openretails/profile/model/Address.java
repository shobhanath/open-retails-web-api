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
