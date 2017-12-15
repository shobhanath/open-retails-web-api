package com.openretails.stock.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

import lombok.Data;

@Entity
@Table(name = TableNames.OFFERS)
@Data
public class Offer extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@ManyToOne
	@JoinColumn(name = "OFFER_TYPE_ID", nullable = false)
	private OfferType offerType;

	@ManyToOne
	@JoinColumn(name = "PROD_ID", nullable = false)
	private Product product;

}
