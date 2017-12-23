package com.openretails.stock.dao.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
					DataAccessMessages.FAILED_CREATE_PROD_CATEGORY + exception.getMessage(), exception.getCause());
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
		final Optional<ProductCategory> optionalUsers = null == flag ? productCategoryRepo.findByIdentity(identity)
				: productCategoryRepo.findByIdentityAndObsolete(identity, flag);
		optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_CATEGORY_NOT_FOUND));
		return optionalUsers.get();
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

	@Cacheable(value = "stock", key = "#name", unless = "#result==null")
	@Override
	public ProductCategory findByName(String name, Boolean flag) {
		final Optional<ProductCategory> optionalUsers = null == flag ? productCategoryRepo.findByNameIgnoreCase(name)
				: productCategoryRepo.findByNameIgnoreCaseAndObsolete(name, flag);
		optionalUsers.orElseThrow(
				() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_CATEGORY_BY_NAME_NOT_FOUND));
		return optionalUsers.get();
	}

	@Override
	public Collection<ProductCategory> findByNameContainingOrIdentityObseleteTrue(String name, Long identity) {
			final Optional<Collection<ProductCategory>> optionalProdCategory = productCategoryRepo
					.findByNameIgnoreCaseContainingOrIdentityAndObsoleteTrue(name, identity);
			optionalProdCategory.orElseThrow(
					() -> new OpenRetailsDataAccessException(DataAccessMessages.SEARCH_BY_NAME_OR_ID_NOT_FOUND));
			return optionalProdCategory.get();

	}

	@Override
	public Collection<Long> findIdByName(Collection<String> names, Boolean flag) {
		final Optional<Collection<Long>> optionalUserId = null == flag
				? productCategoryRepo.getByNameIgnoreCaseIn(names)
				: productCategoryRepo.getByNameIgnoreCaseInAndObsolete(names, flag);
		optionalUserId.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.ID_BY_NAME_NOT_FOUND));
		return optionalUserId.get();
	}

	@Override
	public Long findIdByName(String name, Boolean flag) {
		final Optional<Long> optionalUserId = null == flag ? productCategoryRepo.getByNameIgnoreCase(name)
				: productCategoryRepo.getByNameIgnoreCaseAndObsolete(name, flag);
		optionalUserId.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.ID_BY_NAME_NOT_FOUND));
		return optionalUserId.get();
	}

	@Override
	public Collection<String> findNameById(Collection<Long> identities, Boolean flag) {
		final Optional<Collection<String>> optionalUserId = null == flag ? productCategoryRepo.getNameByIdIn(identities)
				: productCategoryRepo.getNameByIdInAndObsolete(identities, flag);
		optionalUserId.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.NAME_BY_ID_NOT_FOUND));
		return optionalUserId.get();
	}

	@Override
	public String findNameById(Long identity, Boolean flag) {
		final Optional<String> optionalUserId = null == flag ? productCategoryRepo.getNameById(identity)
				: productCategoryRepo.getNameByIdAndObsolete(identity, flag);
		optionalUserId.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.NAME_BY_ID_NOT_FOUND));
		return optionalUserId.get();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Collection<ProductCategory> update(Collection<ProductCategory> productCategories) {
		try {
			productCategories.forEach(productCategory -> findById(productCategory.getIdentity(), null));
			return productCategoryRepo.save(productCategories);
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(
					DataAccessMessages.FAILED_UPDATE_PROD_CATEGORIES + exception.getMessage(),
					exception.getCause());
		}
	}

}
