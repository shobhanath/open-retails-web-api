package com.openretails.data;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductCategoryDTO extends BaseDTO {

	private static final long serialVersionUID = -3578663668010710336L;

	private String name;

	private double gst;
	
	private Set<ProductDTO> product;

}
