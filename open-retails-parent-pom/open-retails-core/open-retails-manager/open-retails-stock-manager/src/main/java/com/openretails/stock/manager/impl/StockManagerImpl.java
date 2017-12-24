package com.openretails.stock.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsBusinessException;
import com.openretails.data.Collections;
import com.openretails.data.Single;
import com.openretails.data.StockDTO;
import com.openretails.stock.dao.StockDao;
import com.openretails.stock.manager.StockManager;
import com.openretails.stock.manager.mapper.StockMapper;

@Service(SpringBeanIds.STOCK_MANAGER)
public class StockManagerImpl implements StockManager {

	@Autowired
	private StockDao stockDao;

	@Autowired
	private StockMapper stockMapper;

	@Override
	public Collections<StockDTO> create(Collections<StockDTO> stocks) {
		try {
			return new Collections<>(
					stockMapper.mapDTO(stockDao.create(stockMapper.mapEntity(stocks.getCollection()))));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<StockDTO> enableOrDisable(Collections<Long> productIds, boolean isEnabled) {
		try {
			return new Collections<>(
					stockMapper.mapDTO(stockDao.enableOrDisable(productIds.getCollection(), isEnabled)));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<StockDTO> findAll(Boolean flag) {
		try {
			return new Collections<>(stockMapper.mapDTO(stockDao.findAll(flag)));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<StockDTO> findById(Collections<Long> identities, Boolean flag) {
		try {
			return new Collections<>(stockMapper.mapDTO(stockDao.findById(identities.getCollection(), flag)));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public StockDTO findById(Long identity, Boolean flag) {
		try {
			return stockMapper.map(stockDao.findById(identity, flag));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public StockDTO findByObsoleteTrueOrIdentityOrProductNameOrIdentity(String productName, Long identity) {
		try {
			return stockMapper.map(stockDao.findByObsoleteTrueOrIdentityOrProductNameOrIdentity(productName, identity));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<StockDTO> findByProductId(Collections<Long> productIds, Boolean flag) {
		try {
			return new Collections<>(stockMapper.mapDTO(stockDao.findByProductId(productIds.getCollection(), flag)));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public StockDTO findByProductId(Long productId, Boolean flag) {
		try {
			return stockMapper.map(stockDao.findByProductId(productId, flag));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<StockDTO> findByProductNameContainingOrIdentityObseleteTrue(String productName, Long identity) {
		try {
			return new Collections<>(stockMapper
					.mapDTO(stockDao.findByProductNameContainingOrIdentityObseleteTrue(productName, identity)));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<Long> findIdByProductName(Collections<String> productNames, Boolean flag) {
		try {
			return new Collections<>(stockDao.findIdByProductName(productNames.getCollection(), flag));
		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Single<Long> findIdByProductName(String productName, Boolean flag) {
		try {
			return new Single<>(stockDao.findIdByProductName(productName, flag));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<String> findProductNameById(Collections<Long> identities, Boolean flag) {
		try {
			return new Collections<>(stockDao.findProductNameById(identities.getCollection(), flag));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Single<String> findProductNameById(Long identity, Boolean flag) {
		try {
			return new Single<>(stockDao.findProductNameById(identity, flag));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<StockDTO> update(Collections<StockDTO> stocks) {
		try {
			return new Collections<>(
					stockMapper.mapDTO(stockDao.update(stockMapper.mapEntity(stocks.getCollection()))));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

}
