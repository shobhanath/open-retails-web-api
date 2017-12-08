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

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 5537554836633470687L;

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
		final BaseEntity other = (BaseEntity) obj;
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (identity == null) {
			if (other.identity != null) {
				return false;
			}
		} else if (!identity.equals(other.identity)) {
			return false;
		}
		if (modified == null) {
			if (other.modified != null) {
				return false;
			}
		} else if (!modified.equals(other.modified)) {
			return false;
		}
		if (obsolete != other.obsolete) {
			return false;
		}
		return true;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (created == null ? 0 : created.hashCode());
		result = prime * result + (identity == null ? 0 : identity.hashCode());
		result = prime * result + (modified == null ? 0 : modified.hashCode());
		result = prime * result + (obsolete ? 1231 : 1237);
		return result;
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
