package com.openretails.stock.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.stock.dao.StockDao;

@Repository(SpringBeanIds.USER_DAO)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StockDaoImpl implements StockDao {

}
