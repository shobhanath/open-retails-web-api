package com.openretails.stock.manager;

import com.openretails.data.Collections;
import com.openretails.data.ProductCategoryDTO;
import com.openretails.data.Single;

public interface ProductCategoryManager {

	Collections<ProductCategoryDTO> create(Collections<ProductCategoryDTO> productCategories);

	Collections<ProductCategoryDTO> enableOrDisable(Collections<String> names, boolean isEnabled);

	Collections<ProductCategoryDTO> findAll(Boolean flag);

	Collections<ProductCategoryDTO> findById(Collections<Long> identities, Boolean flag);

	ProductCategoryDTO findById(Long identity, Boolean flag);

	Collections<ProductCategoryDTO> findByName(Collections<String> names, Boolean flag);

	ProductCategoryDTO findByName(String name, Boolean flag);

	Collections<Long> findIdByName(Collections<String> names, Boolean flag);

	Single<Long> findIdByName(String name, Boolean flag);

	Collections<String> findNameById(Collections<Long> identities, Boolean flag);

	Single<String> findNameById(Long identity, Boolean flag);

	Collections<ProductCategoryDTO> update(Collections<ProductCategoryDTO> productCategories);

}
