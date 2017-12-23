package com.openretails.stock.dao;

import java.util.Collection;

import com.openretails.stock.model.Stock;

public interface StockDao {

	Collection<Stock> create(Collection<Stock> stocks);

	Collection<Stock> enableOrDisable(Collection<Long> productIds, boolean isEnabled);

	Collection<Stock> findAll(Boolean flag);

	Collection<Stock> findById(Collection<Long> identities, Boolean flag);

	Stock findById(Long identity, Boolean flag);

	Collection<Stock> findByObsoleteTrueOrIdentityOrProductNameOrIdentity(String productName,Long identity);

	Collection<Stock> findByProductId(Collection<Long> productIds, Boolean flag);

	Stock findByProductId(Long productId, Boolean flag);

	Collection<Stock> findByProductNameContainingOrIdentityObseleteTrue(String productName, Long identity);

	Collection<Long> findIdByProductName(Collection<String> productNames, Boolean flag);

	Long findIdByProductName(String productName, Boolean flag);

	Collection<String> findProductNameById(Collection<Long> identity, Boolean flag);

	String findProductNameById(Long identity, Boolean flag);

	Collection<Stock> update(Collection<Stock> stocks);

}
