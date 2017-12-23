package com.openretails.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.openretails.common.constant.DataAccessMessages;
import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = TableNames.PRODUCTS)
@Data
@EqualsAndHashCode(callSuper=true)
public class Product extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@NotNull(message = DataAccessMessages.VALIDATE_NAME)
	@Size(min = 2, max = 255, message = DataAccessMessages.VALIDATE_NAME_SIZE)
	@Column
	private String name;

	@NotNull(message = DataAccessMessages.VALIDATE_NAME)
	@Column
	private String barCode;

	@NotNull(message = DataAccessMessages.VALIDATE_PRODUCT_CAT_NOT_NULL)
	@ManyToOne
	@JoinColumn(name="PROD_CATEGORY_ID", nullable=false)
	private ProductCategory productCategory;
}
