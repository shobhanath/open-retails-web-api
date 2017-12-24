package com.openretails.stock.manager;

import com.openretails.data.Collections;
import com.openretails.data.Single;
import com.openretails.data.StockDTO;

public interface StockManager {

	Collections<StockDTO> create(Collections<StockDTO> stocks);

	Collections<StockDTO> enableOrDisable(Collections<Long> productIds, boolean isEnabled);

	Collections<StockDTO> findAll(Boolean flag);

	Collections<StockDTO> findById(Collections<Long> identities, Boolean flag);

	StockDTO findById(Long identity, Boolean flag);

	StockDTO findByObsoleteTrueOrIdentityOrProductNameOrIdentity(String productName, Long identity);

	Collections<StockDTO> findByProductId(Collections<Long> productIds, Boolean flag);

	StockDTO findByProductId(Long productId, Boolean flag);

	Collections<StockDTO> findByProductNameContainingOrIdentityObseleteTrue(String productName, Long identity);

	Collections<Long> findIdByProductName(Collections<String> productNames, Boolean flag);

	Single<Long> findIdByProductName(String productName, Boolean flag);

	Collections<String> findProductNameById(Collections<Long> identity, Boolean flag);

	Single<String> findProductNameById(Long identity, Boolean flag);

	Collections<StockDTO> update(Collections<StockDTO> stocks);

}
