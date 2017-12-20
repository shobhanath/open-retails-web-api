package com.openretails.stock.dao;

import java.util.Collection;

import com.openretails.stock.model.Product;

public interface ProductDao {

	Collection<Product> create(Collection<Product> products);

	Collection<Product> enableOrDisable(Collection<String> names, boolean isEnabled);

	Collection<Product> findAll(Boolean flag);

	Collection<Product> findById(Collection<Long> identity, Boolean flag);

	Product findById(Long identity, Boolean flag);

	Collection<Product> findByName(Collection<String> names, Boolean flag);

	Product findByName(String name, Boolean flag);

	Collection<Product> findByNameContainingOrIdentityObseleteTrue(String name, Long identity);

	Collection<Long> findIdByName(Collection<String> names, Boolean flag);

	Long findIdByName(String name, Boolean flag);

	Collection<String> findNameById(Collection<Long> identity, Boolean flag);

	String findNameById(Long identity, Boolean flag);

	Collection<Product> update(Collection<Product> products);

}
