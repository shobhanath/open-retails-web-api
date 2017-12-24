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
import com.openretails.stock.dao.StockDao;
import com.openretails.stock.model.Stock;
import com.openretails.stock.repository.StockRepository;

@Repository(SpringBeanIds.STOCK_DAO)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StockDaoImpl implements StockDao {

	@Autowired
	private StockRepository stockRepo;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Collection<Stock> create(Collection<Stock> stocks) {
		try {
			return stockRepo.save(stocks);
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(DataAccessMessages.FAILED_CREATE_STOCK + exception.getMessage(),
					exception.getCause());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Collection<Stock> enableOrDisable(Collection<Long> productIds, boolean active) {
		try {
			return stockRepo.save(findByProductId(productIds, null).stream().map(stock -> {
				stock.setObsolete(active);
				return stock;
			}).collect(Collectors.toList()));
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(
					active ? DataAccessMessages.FAILED_TO_ENABLE_STOCK
							: DataAccessMessages.FAILED_TO_DISABLE_STOCK + exception.getMessage(),
					exception.getCause());
		}

	}

	@Override
	public Collection<Stock> findAll(Boolean flag) {
		final Optional<Collection<Stock>> optionalStocks = null == flag ? Optional.of(stockRepo.findAll())
				: stockRepo.findByObsolete(flag);
		optionalStocks.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStocks.get();
	}

	@Override
	public Collection<Stock> findById(Collection<Long> identities, Boolean flag) {
		final Optional<Collection<Stock>> optionalStocks = null == flag ? stockRepo.findByIdentityIn(identities)
				: stockRepo.findByIdentityInAndObsolete(identities, flag);
		optionalStocks.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStocks.get();
	}

	@Override
	public Stock findById(Long identity, Boolean flag) {
		final Optional<Stock> optionalStock = null == flag ? stockRepo.findByIdentity(identity)
				: stockRepo.findByIdentityAndObsolete(identity, flag);
		optionalStock.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStock.get();
	}

	@Override
	public Stock findByObsoleteTrueOrIdentityOrProductNameOrIdentity(String productName, Long identity) {
		final Optional<Stock> optionalStock = stockRepo.findByObsoleteTrueOrIdentityOrProductNameOrIdentity(productName,
				identity);
		optionalStock.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStock.get();
	}

	@Override
	public Collection<Stock> findByProductId(Collection<Long> productIds, Boolean flag) {
		final Optional<Collection<Stock>> optionalStocks = null == flag ? stockRepo.findByProductIdIn(productIds)
				: stockRepo.findByProductIdInAndObsolete(productIds, flag);
		optionalStocks.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStocks.get();
	}

	@Override
	public Stock findByProductId(Long productId, Boolean flag) {
		final Optional<Stock> optionalStocks = null == flag ? stockRepo.findByProductId(productId)
				: stockRepo.findByProductIdAndObsolete(productId, flag);
		optionalStocks.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStocks.get();
	}

	@Override
	public Collection<Stock> findByProductNameContainingOrIdentityObseleteTrue(String productName, Long identity) {
		final Optional<Collection<Stock>> optionalStocks = stockRepo
				.findByProductNameContainingOrIdentityObseleteTrue(productName, identity);
		optionalStocks.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStocks.get();
	}

	@Override
	public Collection<Long> findIdByProductName(Collection<String> productNames, Boolean flag) {
		productNames.stream().map(productName -> productName.toLowerCase());
		final Optional<Collection<Long>> optionalStocks = null == flag ? stockRepo.findByProductNameIn(productNames)
				: stockRepo.findByProductNameInAndObsolete(productNames, flag);
		optionalStocks.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStocks.get();
	}

	@Override
	public Long findIdByProductName(String productName, Boolean flag) {
		final Optional<Long> optionalStocks = null == flag ? stockRepo.findByProductName(productName)
				: stockRepo.findByProductNameAndObsolete(productName, flag);
		optionalStocks.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStocks.get();
	}

	@Override
	public Collection<String> findProductNameById(Collection<Long> identities, Boolean flag) {
		final Optional<Collection<String>> optionalStocks = null == flag ? stockRepo.findProductNameByIdIn(identities)
				: stockRepo.findProductNameByIdInAndObsolete(identities, flag);
		optionalStocks.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStocks.get();
	}

	@Override
	public String findProductNameById(Long identity, Boolean flag) {
		final Optional<String> optionalStocks = null == flag ? stockRepo.findProductNameById(identity)
				: stockRepo.findProductNameByIdAndObsolete(identity, flag);
		optionalStocks.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.STOCK_NOT_FOUND));
		return optionalStocks.get();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Collection<Stock> update(Collection<Stock> stocks) {
		try {
			stocks.forEach(stock -> findById(stock.getIdentity(), null));
			return stockRepo.save(stocks);
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(DataAccessMessages.FAILED_UPDATE_STOCKS + exception.getMessage(),
					exception.getCause());
		}
	}

}
