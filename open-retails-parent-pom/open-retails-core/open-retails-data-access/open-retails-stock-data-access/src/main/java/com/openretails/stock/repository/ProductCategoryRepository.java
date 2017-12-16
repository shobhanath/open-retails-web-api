package com.openretails.stock.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.openretails.stock.model.ProductCategory;

@Repository
public interface ProductCategoryRepository extends BaseJpaRepository<ProductCategory, Long> {

	Optional<ProductCategory> findByNameIgnoreCase(String name);

	Optional<ProductCategory> findByNameIgnoreCaseAndObsolete(String name, boolean active);

	Optional<Collection<ProductCategory>> findByNameIgnoreCaseIn(Collection<String> names);

	Optional<Collection<ProductCategory>> findByNameIgnoreCaseInAndObsolete(Collection<String> names, boolean active);




}
