package com.openretails.stock.model.support;

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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 5537554836633470687L;

	public static final String IDENTITY = "identity";

	/** The identity. */
	@Id
	@Column(name = "entity_id", unique = true, nullable = false)
	@SequenceGenerator(name = "OR_STOCK_HIBERNATE_SEQ", sequenceName = "OR_STOCK_HIBERNATE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OR_STOCK_HIBERNATE_SEQ")
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

}
