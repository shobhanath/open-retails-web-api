package com.openretails.stock.manager;

import com.openretails.data.Collections;
import com.openretails.data.ProductDTO;
import com.openretails.data.Single;

public interface ProductManager {

	Collections<ProductDTO> create(Collections<ProductDTO> products);

	Collections<ProductDTO> enableOrDisable(Collections<String> names, boolean isEnabled);

	Collections<ProductDTO> findAll(Boolean flag);

	Collections<ProductDTO> findById(Collections<Long> identities, Boolean flag);

	ProductDTO findById(Long identity, Boolean flag);

	Collections<ProductDTO> findByName(Collections<String> names, Boolean flag);

	ProductDTO findByName(String name, Boolean flag);

	Collections<ProductDTO> findByNameContainingOrIdentityObseleteTrue(String name, Long identity);

	Collections<Long> findIdByName(Collections<String> names, Boolean flag);

	Single<Long> findIdByName(String name, Boolean flag);

	Collections<String> findNameById(Collections<Long> identities, Boolean flag);

	Single<String> findNameById(Long identity, Boolean flag);

	Collections<ProductDTO> update(Collections<ProductDTO> products);

}
