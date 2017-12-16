package com.openretails.stock.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.openretails.common.constant.DataAccessMessages;
import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = TableNames.OFFERS)
@Data
@EqualsAndHashCode(callSuper=true)
public class Offer extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@NotNull(message = DataAccessMessages.VALIDATE_OFFER_TYPE_NOT_NULL)
	@ManyToOne
	@JoinColumn(name = "OFFER_TYPE_ID", nullable = false)
	private OfferType offerType;

	@NotNull(message = DataAccessMessages.VALIDATE_PRODUCT_NOT_NULL)
	@ManyToOne
	@JoinColumn(name = "PROD_ID", nullable = false)
	private Product product;

}
