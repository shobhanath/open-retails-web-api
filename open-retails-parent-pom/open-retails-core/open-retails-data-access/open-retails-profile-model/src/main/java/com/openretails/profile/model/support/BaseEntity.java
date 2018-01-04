package com.openretails.profile.model.support;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = -3636903859095113970L;

	public static final String IDENTITY = "identity";

	/** The identity. */
	@Id
	@Column(name = "entity_id", unique = true, nullable = false)
	@SequenceGenerator(name = "OR_PROFILE_HIBERNATE_SEQ", sequenceName = "OR_PROFILE_HIBERNATE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OR_PROFILE_HIBERNATE_SEQ")
	private Long identity;

	@Column(name = "OBSOLETE", nullable = false)
	private boolean obsolete;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, updatable = false)
	@CreatedDate
	private Date created;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified")
	@LastModifiedDate
	private Date modified;

	

	/*
	 * @CreatedBy
	 *
	 * @ManyToOne private User createdBy;
	 *
	 * @LastModifiedBy
	 *
	 * @ManyToOne private User modifiedBy;
	 */
	/**
	 * creation date for a model entity object.
	 *
	 * @return created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * primary identity for a model entity object.
	 *
	 * @return identity
	 */
	public Long getIdentity() {
		return identity;
	}

	/*
	 * public User getCreatedBy() { return createdBy; }
	 */

	/**
	 * deletion date for a model entity object.
	 *
	 * @return deleted
	 */
	public Date getModified() {
		return modified;
	}

	/*
	 * public User getModifiedBy() { return modifiedBy; }
	 */

	public boolean isObsolete() {
		return obsolete;
	}

	/**
	 * set the creation date for a model entity object.
	 *
	 * @param value created
	 */
	public void setCreated(Date value) {
		created = value;
	}

	/*
	 * public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
	 */

	/**
	 * set the primary identity for a model entity object.
	 *
	 * @param value identity
	 */
	public void setIdentity(Long value) {
		identity = value;
	}

	/**
	 * set the deletion date for a model entity object.
	 *
	 * @param value deleted
	 */
	public void setModified(Date value) {
		modified = value;
	}

	/*
	 * public void setModifiedBy(User modifiedBy) { this.modifiedBy =
	 * modifiedBy; }
	 */

	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
	}

	/**
	 * Returns a string representation of this instance.
	 *
	 * @return a string representation of this instance
	 */
	@Override
	public String toString() {
		final ToStringBuilder toStringBuilder = new ToStringBuilder(this);
		toStringBuilder.append(IDENTITY, getIdentity());
		return toStringBuilder.toString();
	}
}
