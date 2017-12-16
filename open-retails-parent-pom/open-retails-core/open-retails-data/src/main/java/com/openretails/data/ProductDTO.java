package com.openretails.data;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductDTO extends BaseDTO {

	private static final long serialVersionUID = -3578663668010710336L;

	private String name;

	private String barCode;

	private ProductCategoryDTO productCategory;
	
	private Set<StockDTO> stock;

}
