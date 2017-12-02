package com.openretails.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.openretails.profile.model.support.BaseEntity;
import com.openretails.profile.model.support.TableNames;

@Entity
@Table(name = TableNames.ROLE)
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	private String name;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Role other = (Role) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	public void setName(String name) {
		this.name = name;
	}
}
