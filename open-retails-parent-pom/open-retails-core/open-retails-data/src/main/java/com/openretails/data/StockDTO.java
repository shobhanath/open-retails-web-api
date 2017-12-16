package com.openretails.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class StockDTO extends BaseDTO {

	private static final long serialVersionUID = -3578663668010710336L;

	private ProductDTO product;

	long quantity;

	long buyingPrice;

	long mrp;

}
