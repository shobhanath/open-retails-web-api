package com.openretails.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class OfferTypeDTO extends BaseDTO {
	private static final long serialVersionUID = -7672461603312917433L;
	
	private String name;
	
	private double percentageDiscount;
}
