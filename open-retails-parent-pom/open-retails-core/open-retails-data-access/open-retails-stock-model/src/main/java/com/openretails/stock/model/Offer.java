package com.openretails.stock.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

@Entity
@Table(name = TableNames.OFFERS)
public class Offer extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;



}
