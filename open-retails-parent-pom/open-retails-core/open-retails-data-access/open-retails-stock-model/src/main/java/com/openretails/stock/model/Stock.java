package com.openretails.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.openretails.common.constant.DataAccessMessages;
import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = TableNames.STOCKS)
@Data
@EqualsAndHashCode(callSuper=true)
public class Stock extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@ManyToOne
	@JoinColumn(name = "PROD_ID", nullable = false)
	private Product product;

	@Min(value = 0, message = DataAccessMessages.VALIDATE_MIN_QTY)
	@Column
	long quantity;

	@Min(value = 0, message = DataAccessMessages.VALIDATE_MIN_BUY_PRICE)
	@Column
	long buyingPrice;

	@Min(value = 0, message = DataAccessMessages.VALIDATE_MIN_MRP)
	@Column
	long mrp;

}
