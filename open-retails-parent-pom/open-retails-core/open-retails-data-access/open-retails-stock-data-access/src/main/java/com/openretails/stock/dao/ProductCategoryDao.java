package com.openretails.stock.dao;

import java.util.Collection;

import com.openretails.stock.model.ProductCategory;

public interface ProductCategoryDao {

	Collection<ProductCategory> create(Collection<ProductCategory> productCategories);

	Collection<ProductCategory> enableOrDisable(Collection<String> names, boolean isEnabled);

	Collection<ProductCategory> findAll(Boolean flag);

	Collection<ProductCategory> findById(Collection<Long> identity, Boolean flag);

	ProductCategory findById(Long identity, Boolean flag);

	Collection<ProductCategory> findByName(Collection<String> names, Boolean flag);

	ProductCategory findByName(String name, Boolean flag);

	Collection<ProductCategory> findByNameContainingOrIdentityObseleteTrue(String name, Long identity);

	Collection<Long> findIdByName(Collection<String> names, Boolean flag);

	Long findIdByName(String name, Boolean flag);

	Collection<String> findNameById(Collection<Long> identity, Boolean flag);

	String findNameById(Long identity, Boolean flag);

	Collection<ProductCategory> update(Collection<ProductCategory> productCategories);

}
