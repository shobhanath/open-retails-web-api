package com.openretails.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_PROFILE_API_INFO")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProfileApiInfo {
	
	@Id
	@Column(name = "entity_id", unique = true, nullable = false)
	@SequenceGenerator(name = "OR_PROFILE_HIBERNATE_SEQ", sequenceName = "OR_PROFILE_HIBERNATE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OR_PROFILE_HIBERNATE_SEQ")
	private Long identity;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "API_INFO_ID", referencedColumnName = "entity_id")})
	private ApiInfo apiInfo;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "PROFILE_ACCESS_ID", referencedColumnName = "entity_id")})
	private ProfileAccess profileAccess;
}
