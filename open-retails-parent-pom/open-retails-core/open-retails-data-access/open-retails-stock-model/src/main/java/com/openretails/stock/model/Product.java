package com.openretails.stock.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

import lombok.Data;

@Entity
@Table(name = TableNames.PRODUCTS)
@Data
public class Product extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@Column
	private String name;

	@Column
	private String barCode;

	@ManyToOne
	@JoinColumn(name="PROD_CATEGORY_ID", nullable=false)
	private ProductCategory productCategory;
	
	@OneToMany(mappedBy="product")
	private Set<Stock> stock;

}
