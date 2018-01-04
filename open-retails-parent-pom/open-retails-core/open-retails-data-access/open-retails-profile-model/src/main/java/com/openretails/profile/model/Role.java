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
@Table(name = TableNames.ROLES)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	private String name;

}
