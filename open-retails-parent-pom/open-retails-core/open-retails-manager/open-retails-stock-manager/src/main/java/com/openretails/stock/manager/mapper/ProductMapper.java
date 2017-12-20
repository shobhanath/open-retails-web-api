package com.openretails.stock.manager.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.data.ProductDTO;
import com.openretails.stock.model.Product;

@Component(SpringBeanIds.PRODUCT_MAPPER)
public class ProductMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductDTO map(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}

	public Product map(ProductDTO productDTO) {
		productDTO.setName(StringUtils.isNotBlank(productDTO.getName()) ? productDTO.getName().trim().toLowerCase()
				: productDTO.getName());
		return modelMapper.map(productDTO, Product.class);
	}

	public Collection<ProductDTO> mapDTO(Collection<Product> products) {
		return products.stream().map(product -> map(product)).collect(Collectors.toList());
	}

	public Collection<Product> mapEntity(Collection<ProductDTO> productsDTO) {
		return productsDTO.stream().map(product -> map(product)).collect(Collectors.toList());
	}

}
