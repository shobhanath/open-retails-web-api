package com.openretails.stock.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

import lombok.Data;

@Entity
@Table(name = TableNames.PROD_CATEGORIES)
@Data
public class ProductCategory extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@Column
	private String name;

	@Column
	private double gst;
	
	@OneToMany(mappedBy="productCategory")
	private Set<Product> product;

}
