package com.openretails.stock.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.openretails.common.constant.DataAccessMessages;
import com.openretails.stock.model.support.BaseEntity;
import com.openretails.stock.model.support.TableNames;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = TableNames.PROD_CATEGORIES)
@Data
@EqualsAndHashCode(callSuper=true)
public class ProductCategory extends BaseEntity {

	private static final long serialVersionUID = -3578663668010710336L;

	@NotNull(message = DataAccessMessages.VALIDATE_NAME)
	@Size(min = 2, max = 255, message = DataAccessMessages.VALIDATE_NAME_SIZE)
	@Column
	private String name;

	@Min(value = 0, message = DataAccessMessages.VALIDATE_MIN_GST_PERCENTAGE)
	@Column
	private double gst;
	
	@OneToMany(mappedBy="productCategory")
	private Set<Product> product;

}
