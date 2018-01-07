package com.openretails.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.openretails.profile.model.support.BaseEntity;
import com.openretails.profile.model.support.TableNames;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Table(name = TableNames.ADDRESS)
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{

	private static final long serialVersionUID = 2779885384896272527L;

	@Column(name = "ADDRESS_FREE_TEXT", nullable = false,length=255)
	private String addressFreeText;

	@Column(name = "COMMENT", nullable = false)
	private String comment;

}
