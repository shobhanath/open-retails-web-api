package com.openretails.profile.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

import com.openretails.profile.model.support.BaseEntity;
@Entity
@Table(name = "T_USER_TYPE")
public class UserType extends BaseEntity {

	private static final long serialVersionUID = 5394286088951284958L;

	/** The identity. */
	@Id
	@Column(name = "entity_id", unique = true, nullable = false)
	@SequenceGenerator(name = "OR_PROFILE_HIBERNATE_SEQ", sequenceName = "OR_PROFILE_HIBERNATE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OR_PROFILE_HIBERNATE_SEQ")
	private Long identity;

	/** The created. */
	@NotEmpty
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, updatable = false)
	private Date created;

	/** The modified. */
	@NotEmpty
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified")
	private Date modified;

	@NotEmpty
	@Column(name = "NAME", unique = true, nullable = false, length = 255)
	private String name;

	@Column(name = "COMMENT", nullable = true, length = 255)
	private String comment;

	@NotEmpty
	@Column(name = "OBSOLETE", nullable = false)
	private boolean obsolete;

	public String getComment() {
		return comment;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public Long getIdentity() {
		return identity;
	}

	@Override
	public Date getModified() {
		return modified;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isObsolete() {
		return obsolete;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public void setIdentity(Long identity) {
		this.identity = identity;
	}

	@Override
	public void setModified(Date modified) {
		this.modified = modified;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
	}

}
