package com.openretails.stock.dao.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.constant.DataAccessMessages;
import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsDataAccessException;
import com.openretails.stock.dao.ProductCategoryDao;
import com.openretails.stock.model.ProductCategory;
import com.openretails.stock.repository.ProductCategoryRepository;

@Repository(SpringBeanIds.PRODUCT_CATEGORY_DAO)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductCategoryDaoImpl implements ProductCategoryDao {

	@Autowired
	private ProductCategoryRepository productCategoryRepo;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Collection<ProductCategory> create(Collection<ProductCategory> productCategories) {
		try {
			return productCategoryRepo.save(productCategories);
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(
					DataAccessMessages.FAILED_CREATE_PROD_CATEGORY + exception.getMessage(),
					exception.getCause());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Collection<ProductCategory> enableOrDisable(Collection<String> names, boolean isEnabled) {
		try {
			return productCategoryRepo.save(findByName(names, null).stream().map(productCategory -> {
				productCategory.setObsolete(isEnabled);
				return productCategory;
			}).collect(Collectors.toList()));
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(
					isEnabled ? DataAccessMessages.FAILED_TO_ENABLE_PROD_CATEGORY
							: DataAccessMessages.FAILED_TO_DISABLE_PROD_CATEGORY + exception.getMessage(),
					exception.getCause());
		}

	}

	@Override
	public Collection<ProductCategory> findAll(Boolean flag) {
		final Optional<Collection<ProductCategory>> optionalUsers = null == flag
				? Optional.of(productCategoryRepo.findAll()) : productCategoryRepo.findByObsolete(flag);
		optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_CATEGORY_NOT_FOUND));
		return optionalUsers.get();
	}

	@Override
	public Collection<ProductCategory> findById(Collection<Long> identity, Boolean flag) {
		final Optional<Collection<ProductCategory>> optionalUsers = null == flag
				? productCategoryRepo.findByIdentityIn(identity)
				: productCategoryRepo.findByIdentityInAndObsolete(identity, flag);
		optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_CATEGORY_NOT_FOUND));
		return optionalUsers.get();
	}

	@Override
	public ProductCategory findById(Long identity, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ProductCategory> findByName(Collection<String> names, Boolean flag) {
		final Optional<Collection<ProductCategory>> optionalUsers = null == flag
				? productCategoryRepo.findByNameIgnoreCaseIn(names)
				: productCategoryRepo.findByNameIgnoreCaseInAndObsolete(names, flag);
		optionalUsers.orElseThrow(
				() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_CATEGORY_BY_NAME_NOT_FOUND));
		return optionalUsers.get();
	}

	@Override
	public ProductCategory findByName(String name, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Long> findIdByName(Collection<String> names, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findIdByName(String name, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> findNameById(Collection<Long> identity, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findNameById(Long identity, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ProductCategory> update(Collection<ProductCategory> productCategories) {
		// TODO Auto-generated method stub
		return null;
	}

}
