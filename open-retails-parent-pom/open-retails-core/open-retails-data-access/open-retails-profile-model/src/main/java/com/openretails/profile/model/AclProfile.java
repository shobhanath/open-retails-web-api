package com.openretails.profile.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.openretails.profile.model.support.BaseEntity;

@Entity
@Table(name = "ACL_PROFILE")
public class AclProfile extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1102125972507820424L;

	/** The Constant HASH_CODE_PRIMES. */
	private static final int[] HASH_CODE_PRIMES = { 17, 61 };

	/** The description. */
	private String description;

	/** The maximum failed logins. */
	@Column(name = "MAXIMUM_FAILED_LOGINS")
	private Integer maximumFailedLogins;

	/** The maximum password age. */
	@Column(name = "MAXIMUM_PASSWORD_AGE")
	private Integer maximumPasswordAge;

	/** The maximum password length. */
	@Column(name = "MAXIMUM_PASSWORD_LENGTH")
	private Integer maximumPasswordLength;

	/** The minimum lowercase letters. */
	@Column(name = "MINIMUM_LOWERCASE_LETTERS")
	private Integer minimumLowercaseLetters;

	/** The minimum number characters. */
	@Column(name = "MINIMUM_NUMBER_CHARACTERS")
	private Integer minimumNumberCharacters;

	/** The minimum password age. */
	@Column(name = "MINIMUM_PASSWORD_AGE")
	private Integer minimumPasswordAge;

	/** The minimum password length. */
	@Column(name = "MINIMUM_PASSWORD_LENGTH")
	private Integer minimumPasswordLength;

	/** The minimum password reuse. */
	@Column(name = "MINIMUM_PASSWORD_REUSE")
	private Integer minimumPasswordReuse;

	/** The minimum special characters. */
	@Column(name = "MINIMUM_SPECIAL_CHARACTERS")
	private Integer minimumSpecialCharacters;

	/** The minimum uppercase letters. */
	@Column(name = "MINIMUM_UPPERCASE_LETTERS")
	private Integer minimumUppercaseLetters;

	/** The name. */
	private String name;

	/** The password change warn. */
	@Column(name = "PASSWORD_CHANGE_WARN")
	private Integer passwordChangeWarn;

	/** The password grace period. */
	@Column(name = "PASSWORD_GRACE_PERIOD")
	private Integer passwordGracePeriod;

	/** The special characters. */
	@Column(name = "SPECIAL_CHARACTERS")
	private String specialCharacters;

	/** The version. */
	private Integer version;

	/**
	 * Instantiates a new acl profile.
	 */
	public AclProfile() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AclProfile)) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		final AclProfile rhs = (AclProfile) obj;
		return new EqualsBuilder().append(name, rhs.getName()).isEquals();
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the maximum failed logins.
	 *
	 * @return the maximum failed logins
	 */
	public Integer getMaximumFailedLogins() {
		return maximumFailedLogins;
	}

	/**
	 * Gets the maximum password age.
	 *
	 * @return the maximum password age
	 */
	public Integer getMaximumPasswordAge() {
		return maximumPasswordAge;
	}

	/**
	 * Gets the maximum password length.
	 *
	 * @return the maximum password length
	 */
	public Integer getMaximumPasswordLength() {
		return maximumPasswordLength;
	}

	/**
	 * Gets the minimum lowercase letters.
	 *
	 * @return the minimum lowercase letters
	 */
	public Integer getMinimumLowercaseLetters() {
		return minimumLowercaseLetters;
	}

	/**
	 * Gets the minimum number characters.
	 *
	 * @return the minimum number characters
	 */
	public Integer getMinimumNumberCharacters() {
		return minimumNumberCharacters;
	}

	/**
	 * Gets the minimum password age.
	 *
	 * @return the minimum password age
	 */
	public Integer getMinimumPasswordAge() {
		return minimumPasswordAge;
	}

	/**
	 * Gets the minimum password length.
	 *
	 * @return the minimum password length
	 */
	public Integer getMinimumPasswordLength() {
		return minimumPasswordLength;
	}

	/**
	 * Gets the minimum password reuse.
	 *
	 * @return the minimum password reuse
	 */
	public Integer getMinimumPasswordReuse() {
		return minimumPasswordReuse;
	}

	/**
	 * Gets the minimum special characters.
	 *
	 * @return the minimum special characters
	 */
	public Integer getMinimumSpecialCharacters() {
		return minimumSpecialCharacters;
	}

	/**
	 * Gets the minimum uppercase letters.
	 *
	 * @return the minimum uppercase letters
	 */
	public Integer getMinimumUppercaseLetters() {
		return minimumUppercaseLetters;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the password change warn.
	 *
	 * @return the password change warn
	 */
	public Integer getPasswordChangeWarn() {
		return passwordChangeWarn;
	}

	/**
	 * Gets the password grace period.
	 *
	 * @return the password grace period
	 */
	public Integer getPasswordGracePeriod() {
		return passwordGracePeriod;
	}

	/**
	 * Gets the special characters.
	 *
	 * @return the special characters
	 */
	public String getSpecialCharacters() {
		return specialCharacters;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(HASH_CODE_PRIMES[0], HASH_CODE_PRIMES[1]).append(getName()).toHashCode();
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the maximum failed logins.
	 *
	 * @param maximumFailedLogins
	 *            the new maximum failed logins
	 */
	public void setMaximumFailedLogins(Integer maximumFailedLogins) {
		this.maximumFailedLogins = maximumFailedLogins;
	}

	/**
	 * Sets the maximum password age.
	 *
	 * @param maximumPasswordAge
	 *            the new maximum password age
	 */
	public void setMaximumPasswordAge(Integer maximumPasswordAge) {
		this.maximumPasswordAge = maximumPasswordAge;
	}

	/**
	 * Sets the maximum password length.
	 *
	 * @param maximumPasswordLength
	 *            the new maximum password length
	 */
	public void setMaximumPasswordLength(Integer maximumPasswordLength) {
		this.maximumPasswordLength = maximumPasswordLength;
	}

	/**
	 * Sets the minimum lowercase letters.
	 *
	 * @param minimumLowercaseLetters
	 *            the new minimum lowercase letters
	 */
	public void setMinimumLowercaseLetters(Integer minimumLowercaseLetters) {
		this.minimumLowercaseLetters = minimumLowercaseLetters;
	}

	/**
	 * Sets the minimum number characters.
	 *
	 * @param minimumNumberCharacters
	 *            the new minimum number characters
	 */
	public void setMinimumNumberCharacters(Integer minimumNumberCharacters) {
		this.minimumNumberCharacters = minimumNumberCharacters;
	}

	/**
	 * Sets the minimum password age.
	 *
	 * @param minimumPasswordAge
	 *            the new minimum password age
	 */
	public void setMinimumPasswordAge(Integer minimumPasswordAge) {
		this.minimumPasswordAge = minimumPasswordAge;
	}

	/**
	 * Sets the minimum password length.
	 *
	 * @param minimumPasswordLength
	 *            the new minimum password length
	 */
	public void setMinimumPasswordLength(Integer minimumPasswordLength) {
		this.minimumPasswordLength = minimumPasswordLength;
	}

	/**
	 * Sets the minimum password reuse.
	 *
	 * @param minimumPasswordReuse
	 *            the new minimum password reuse
	 */
	public void setMinimumPasswordReuse(Integer minimumPasswordReuse) {
		this.minimumPasswordReuse = minimumPasswordReuse;
	}

	/**
	 * Sets the minimum special characters.
	 *
	 * @param minimumSpecialCharacters
	 *            the new minimum special characters
	 */
	public void setMinimumSpecialCharacters(Integer minimumSpecialCharacters) {
		this.minimumSpecialCharacters = minimumSpecialCharacters;
	}

	/**
	 * Sets the minimum uppercase letters.
	 *
	 * @param minimumUppercaseLetters
	 *            the new minimum uppercase letters
	 */
	public void setMinimumUppercaseLetters(Integer minimumUppercaseLetters) {
		this.minimumUppercaseLetters = minimumUppercaseLetters;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the password change warn.
	 *
	 * @param passwordChangeWarn
	 *            the new password change warn
	 */
	public void setPasswordChangeWarn(Integer passwordChangeWarn) {
		this.passwordChangeWarn = passwordChangeWarn;
	}

	/**
	 * Sets the password grace period.
	 *
	 * @param passwordGracePeriod
	 *            the new password grace period
	 */
	public void setPasswordGracePeriod(Integer passwordGracePeriod) {
		this.passwordGracePeriod = passwordGracePeriod;
	}

	/**
	 * Sets the special characters.
	 *
	 * @param specialCharacters
	 *            the new special characters
	 */
	public void setSpecialCharacters(String specialCharacters) {
		this.specialCharacters = specialCharacters;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the new version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getName() + " - min password len :" + getMinimumPasswordLength()
				+ " max password len :" + getMaximumPasswordLength() + " min uppercase letters :"
				+ getMinimumUppercaseLetters() + " min lowercase letters:" + getMinimumLowercaseLetters()
				+ " special characters :" + getSpecialCharacters() + " min special character count :"
				+ getMinimumSpecialCharacters() + " min number count :" + getMinimumNumberCharacters();

	}
}