package com.openretails.stock.manager.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.data.ProductCategoryDTO;
import com.openretails.stock.model.ProductCategory;

@Component(SpringBeanIds.PRODUCT_CATEGORY_MAPPER)
public class ProductCategoryMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductCategoryDTO map(ProductCategory productCategory) {
		return modelMapper.map(productCategory, ProductCategoryDTO.class);
	}

	public ProductCategory map(ProductCategoryDTO productCategoryDTO) {
		productCategoryDTO.setName(StringUtils.isNotBlank(productCategoryDTO.getName())
				? productCategoryDTO.getName().trim().toLowerCase() : productCategoryDTO.getName());
		return modelMapper.map(productCategoryDTO, ProductCategory.class);
	}

	public Collection<ProductCategoryDTO> mapDTO(Collection<ProductCategory> productCategories) {
		return productCategories.stream().map(productCategory -> map(productCategory)).collect(Collectors.toList());
	}

	public Collection<ProductCategory> mapEntity(Collection<ProductCategoryDTO> productCategoriesDTO) {
		return productCategoriesDTO.stream().map(productCategory -> map(productCategory)).collect(Collectors.toList());
	}

}
