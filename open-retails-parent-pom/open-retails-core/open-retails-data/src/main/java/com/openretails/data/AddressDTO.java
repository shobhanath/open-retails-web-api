package com.openretails.data;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName(value = "address")
@Data
@EqualsAndHashCode(callSuper=true)
public class AddressDTO extends BaseDTO {

	private static final long serialVersionUID = 2779885384896272527L;

	private String addressFreeText;
	private String comment;

}
