package com.openretails.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_API_INFO")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ApiInfo {

	@Id
	@Column(name = "entity_id", unique = true, nullable = false)
	@SequenceGenerator(name = "OR_PROFILE_HIBERNATE_SEQ", sequenceName = "OR_PROFILE_HIBERNATE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OR_PROFILE_HIBERNATE_SEQ")
	private Long identity;
	
	@NotNull
	@Column(name = "SERVICE_URI", nullable = false)
	private String serviceUri;
	
	@NotNull
	@Column(name = "SERVICE_CLASS_NAME", nullable = false)
	private String serviceClassName;
	
	@NotNull
	@Column(name = "HTTP_METHOD", nullable = false)
	private HttpMethod httpMethod;
	
	
	@NotNull
	@Column(name = "SERVICE_METHOD_NAME", nullable = false)
	private String serviceMethodName;
	
	@NotNull
	@Column(name = "ROLE_NAME", nullable = false)
	private Role roleName;
	
}
