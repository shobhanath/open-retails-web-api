package com.openretails.data;

public class AddressDTO extends BaseDTO {

	private static final long serialVersionUID = 2779885384896272527L;

	private String addressFreeText;
	private Long pinCode;
	private String countryName;
	private String countryCode;
	private String comment;
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
