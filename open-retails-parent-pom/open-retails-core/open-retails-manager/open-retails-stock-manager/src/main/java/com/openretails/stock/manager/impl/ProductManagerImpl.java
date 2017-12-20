package com.openretails.stock.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsBusinessException;
import com.openretails.data.Collections;
import com.openretails.data.ProductDTO;
import com.openretails.data.Single;
import com.openretails.stock.dao.ProductDao;
import com.openretails.stock.manager.ProductManager;
import com.openretails.stock.manager.mapper.ProductMapper;

import lombok.NonNull;

@Service(SpringBeanIds.PRODUCT_MANAGER)
public class ProductManagerImpl implements ProductManager {

	@Autowired
	private ProductDao productCategoryDao;

	@Autowired
	private ProductMapper productCategoryMapper;

	@Override
	public Collections<ProductDTO> create(@NonNull Collections<ProductDTO> products) {
		try {
			return new Collections<>(productCategoryMapper
					.mapDTO(productCategoryDao.create(productCategoryMapper.mapEntity(products.getCollection()))));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductDTO> enableOrDisable(@NonNull Collections<String> names, boolean isEnabled) {
		try {
			return new Collections<>(
					productCategoryMapper.mapDTO(productCategoryDao.enableOrDisable(names.getCollection(), isEnabled)));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductDTO> findAll(Boolean flag) {
		try {
			return new Collections<>(productCategoryMapper.mapDTO(productCategoryDao.findAll(flag)));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductDTO> findById(@NonNull Collections<Long> identities, Boolean flag) {
		try {
			return new Collections<>(
					productCategoryMapper.mapDTO(productCategoryDao.findById(identities.getCollection(), flag)));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ProductDTO findById(Long identity, Boolean flag) {
		try {
			return productCategoryMapper.map(productCategoryDao.findById(identity, flag));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductDTO> findByName(@NonNull Collections<String> names, Boolean flag) {
		try {
			return new Collections<>(
					productCategoryMapper.mapDTO(productCategoryDao.findByName(names.getCollection(), flag)));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ProductDTO findByName(@NonNull String name, Boolean flag) {
		try {
			return productCategoryMapper.map(productCategoryDao.findByName(name, flag));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<ProductDTO> findByNameContainingOrIdentityObseleteTrue(String name, Long identity) {
		return new Collections<>(productCategoryMapper
				.mapDTO(productCategoryDao.findByNameContainingOrIdentityObseleteTrue(name, identity)));
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
	public Collections<ProductDTO> update(@NonNull Collections<ProductDTO> products) {
		try {
			return new Collections<>(productCategoryMapper
					.mapDTO(productCategoryDao.update(productCategoryMapper.mapEntity(products.getCollection()))));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

}
