package com.openretails.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

import lombok.Data;

@Entity
@Table(name = TableNames.OFFER_TYPES)
@Data
public class OfferType extends BaseEntity {
	private static final long serialVersionUID = -7672461603312917433L;
	@Column
	private String name;
	@Column
	private double percentageDiscount;
}
