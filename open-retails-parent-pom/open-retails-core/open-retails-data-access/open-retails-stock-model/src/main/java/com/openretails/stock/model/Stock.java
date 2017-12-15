package com.openretails.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

@Entity
@Table(name = TableNames.STOCKS)
public class Stock extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@ManyToOne
	@JoinColumn(name = "PROD_ID", nullable = false)
	private Product product;

	@Column
	long quantity;

	@Column
	long buyingPrice;

	@Column
	long mrp;

}
