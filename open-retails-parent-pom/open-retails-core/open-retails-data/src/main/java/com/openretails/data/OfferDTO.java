package com.openretails.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class OfferDTO extends BaseDTO {

	private static final long serialVersionUID = 6224880219614836638L;

	private OfferTypeDTO offerType;

	private ProductDTO product;

}
