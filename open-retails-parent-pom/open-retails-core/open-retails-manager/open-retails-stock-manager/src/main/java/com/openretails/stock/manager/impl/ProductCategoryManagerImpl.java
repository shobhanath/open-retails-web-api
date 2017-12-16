package com.openretails.stock.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsBusinessException;
import com.openretails.data.Collections;
import com.openretails.data.ProductCategoryDTO;
import com.openretails.data.Single;
import com.openretails.stock.dao.ProductCategoryDao;
import com.openretails.stock.manager.ProductCategoryManager;
import com.openretails.stock.manager.mapper.ProductCategoryMapper;

import lombok.NonNull;

@Service(SpringBeanIds.PRODUCT_CATEGORY_MANAGER)
public class ProductCategoryManagerImpl implements ProductCategoryManager {

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Autowired
	private ProductCategoryMapper productCategoryMapper;

	@Override
	public Collections<ProductCategoryDTO> create(@NonNull Collections<ProductCategoryDTO> productCategories) {
		try {
			return new Collections<>(productCategoryMapper.mapDTO(productCategoryDao
					.create(productCategoryMapper.mapEntity(productCategories.getCollection()))));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductCategoryDTO> enableOrDisable(@NonNull Collections<String> names, boolean isEnabled) {
		try {
			return new Collections<>(productCategoryMapper
					.mapDTO(productCategoryDao.enableOrDisable(names.getCollection(), isEnabled)));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductCategoryDTO> findAll(Boolean flag) {
		try {
			return new Collections<>(productCategoryMapper.mapDTO(productCategoryDao.findAll(flag)));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductCategoryDTO> findById(@NonNull Collections<Long> identities, Boolean flag) {
		try {
			return new Collections<>(
					productCategoryMapper.mapDTO(productCategoryDao.findById(identities.getCollection(), flag)));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ProductCategoryDTO findById(Long identity, Boolean flag) {
		try {
			return productCategoryMapper.map(productCategoryDao.findById(identity, flag));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductCategoryDTO> findByName(@NonNull Collections<String> names, Boolean flag) {
		try {
			return new Collections<>(
					productCategoryMapper.mapDTO(productCategoryDao.findByName(names.getCollection(), flag)));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ProductCategoryDTO findByName(@NonNull String name, Boolean flag) {
		try {
			return productCategoryMapper.map(productCategoryDao.findByName(name, flag));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<Long> findIdByName(@NonNull Collections<String> names, Boolean flag) {
		try {
			return new Collections<>(productCategoryDao.findIdByName(names.getCollection(), flag));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Single<Long> findIdByName(@NonNull String name, Boolean flag) {
		try {
			return new Single<>(productCategoryDao.findIdByName(name, flag));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<String> findNameById(@NonNull Collections<Long> identities, Boolean flag) {
		try {
			return new Collections<>(productCategoryDao.findNameById(identities.getCollection(), flag));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Single<String> findNameById(Long identity, Boolean flag) {
		try {
			return new Single<>(productCategoryDao.findNameById(identity, flag));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductCategoryDTO> update(@NonNull Collections<ProductCategoryDTO> productCategories) {
		try {
			return new Collections<>(productCategoryMapper.mapDTO(
					productCategoryDao.update(productCategoryMapper.mapEntity(productCategories.getCollection()))));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

}
