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
import com.openretails.stock.dao.ProductDao;
import com.openretails.stock.model.Product;
import com.openretails.stock.repository.ProductRepository;

@Repository(SpringBeanIds.PRODUCT_DAO)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductRepository productRepo;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Collection<Product> create(Collection<Product> products) {
		try {
			return productRepo.save(products);
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(DataAccessMessages.FAILED_CREATE_PROD + exception.getMessage(),
					exception.getCause());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Collection<Product> enableOrDisable(Collection<String> names, boolean isEnabled) {
		try {
			return productRepo.save(findByName(names, null).stream().map(productCategory -> {
				productCategory.setObsolete(isEnabled);
				return productCategory;
			}).collect(Collectors.toList()));
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(isEnabled ? DataAccessMessages.FAILED_TO_ENABLE_PROD
					: DataAccessMessages.FAILED_TO_DISABLE_PROD + exception.getMessage(), exception.getCause());
		}

	}

	@Override
	public Collection<Product> findAll(Boolean flag) {
		final Optional<Collection<Product>> optionalUsers = null == flag ? Optional.of(productRepo.findAll())
				: productRepo.findByObsolete(flag);
		optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_NOT_FOUND));
		return optionalUsers.get();
	}

	@Override
	public Collection<Product> findById(Collection<Long> identity, Boolean flag) {
		final Optional<Collection<Product>> optionalUsers = null == flag ? productRepo.findByIdentityIn(identity)
				: productRepo.findByIdentityInAndObsolete(identity, flag);
		optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_NOT_FOUND));
		return optionalUsers.get();
	}

	@Override
	public Product findById(Long identity, Boolean flag) {
		final Optional<Product> optionalUsers = null == flag ? productRepo.findByIdentity(identity)
				: productRepo.findByIdentityAndObsolete(identity, flag);
		optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_NOT_FOUND));
		return optionalUsers.get();
	}

	@Override
	public Collection<Product> findByName(Collection<String> names, Boolean flag) {
		final Optional<Collection<Product>> optionalUsers = null == flag ? productRepo.findByNameIgnoreCaseIn(names)
				: productRepo.findByNameIgnoreCaseInAndObsolete(names, flag);
		optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_BY_NAME_NOT_FOUND));
		return optionalUsers.get();
	}

	@Cacheable(value = "stock", key = "#name", unless = "#result==null")
	@Override
	public Product findByName(String name, Boolean flag) {
		final Optional<Product> optionalUsers = null == flag ? productRepo.findByNameIgnoreCase(name)
				: productRepo.findByNameIgnoreCaseAndObsolete(name, flag);
		optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_BY_NAME_NOT_FOUND));
		return optionalUsers.get();
	}

	@Override
	public Collection<Product> findByNameContainingOrIdentityObseleteTrue(String name, Long identity) {
			final Optional<Collection<Product>> optionalProdCategory = productRepo
					.findByNameIgnoreCaseContainingOrIdentityAndObsoleteTrue(name, identity);
			optionalProdCategory.orElseThrow(
					() -> new OpenRetailsDataAccessException(DataAccessMessages.PROD_SEARCH_BY_NAME_OR_ID_NOT_FOUND));
			return optionalProdCategory.get();

	}

	@Override
	public Collection<Long> findIdByName(Collection<String> names, Boolean flag) {
		final Optional<Collection<Long>> optionalUserId = null == flag ? productRepo.getByNameIgnoreCaseIn(names)
				: productRepo.getByNameIgnoreCaseInAndObsolete(names, flag);
		optionalUserId.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.ID_BY_NAME_NOT_FOUND));
		return optionalUserId.get();
	}

	@Override
	public Long findIdByName(String name, Boolean flag) {
		final Optional<Long> optionalUserId = null == flag ? productRepo.getByNameIgnoreCase(name)
				: productRepo.getByNameIgnoreCaseAndObsolete(name, flag);
		optionalUserId.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.ID_BY_NAME_NOT_FOUND));
		return optionalUserId.get();
	}

	@Override
	public Collection<String> findNameById(Collection<Long> identities, Boolean flag) {
		final Optional<Collection<String>> optionalUserId = null == flag ? productRepo.getNameByIdIn(identities)
				: productRepo.getNameByIdInAndObsolete(identities, flag);
		optionalUserId.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.NAME_BY_ID_NOT_FOUND));
		return optionalUserId.get();
	}

	@Override
	public String findNameById(Long identity, Boolean flag) {
		final Optional<String> optionalUserId = null == flag ? productRepo.getNameById(identity)
				: productRepo.getNameByIdAndObsolete(identity, flag);
		optionalUserId.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.NAME_BY_ID_NOT_FOUND));
		return optionalUserId.get();
	}

	@Override
	public Collection<Product> update(Collection<Product> products) {
		try {
			products.forEach(productCategory -> findById(productCategory.getIdentity(), null));
			return productRepo.save(products);
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(DataAccessMessages.FAILED_UPDATE_PRODUCTS + exception.getMessage(),
					exception.getCause());
		}
	}

}
