package com.openretails.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.openretails.common.constant.DataAccessMessages;
import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = TableNames.OFFER_TYPES)
@Data
@EqualsAndHashCode(callSuper=true)
public class OfferType extends BaseEntity {
	private static final long serialVersionUID = -7672461603312917433L;
	
	@NotNull(message = DataAccessMessages.VALIDATE_NAME)
	@Column
	private String name;
	
	@Min(value = 0, message = DataAccessMessages.VALIDATE_MIN_DISCOUNT_PERCENTAGE)
	@Column
	private double percentageDiscount;
}
