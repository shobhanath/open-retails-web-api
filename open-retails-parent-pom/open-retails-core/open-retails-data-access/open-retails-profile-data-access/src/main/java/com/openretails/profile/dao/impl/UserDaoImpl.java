package com.openretails.profile.dao.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.constant.DataAccessMessages;
import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsDataAccessException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.profile.dao.UserDao;
import com.openretails.profile.model.Address;
import com.openretails.profile.model.User;
import com.openretails.profile.repository.UserRepository;

@Repository(SpringBeanIds.USER_DAO)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Collection<User> create(Collection<User> users) {
		try {
			return userRepository.save(encodePassword(users));
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(DataAccessMessages.FAILED_CREATE_USERS + exception.getMessage(),
					exception.getCause());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Collection<User> enableOrDisable(Collection<String> users, boolean isEnabled) {
		try {
			users = users.stream().map(user -> {
				return user.trim().toLowerCase();
			}).collect(Collectors.toList());
			final Optional<Collection<User>> optionalUsers = userRepository.findByUsernameOrPrimaryEmailId(users,
					users);
			optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.USERS_NOT_FOUND));
			final Collection<User> userCollection = optionalUsers.get().stream().map(existingUser -> {
				existingUser.setObsolete(isEnabled);
				return existingUser;
			}).collect(Collectors.toList());
			return userRepository.save(userCollection);
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(
					isEnabled ? DataAccessMessages.FAILED_TO_ENABLE_USERS
							: DataAccessMessages.FAILED_TO_DISABLE_USERS + exception.getMessage(),
					exception.getCause());
		}
	}

	private Collection<User> encodePassword(Collection<User> users) {
		final Collection<User> userCollection = users.stream().map(existingUser -> {
			existingUser.setPassword(passwordEncoder.encode(existingUser.getPassword()));
			return existingUser;
		}).collect(Collectors.toList());
		return userCollection;
	}

	@Override
	public Collection<User> findAll(Boolean flag) {
		try {
			final Optional<Collection<User>> optionalUsers = null == flag ? userRepository.getAll()
					: userRepository.findAll(flag);
			optionalUsers.orElseThrow(
					() -> new OpenRetailsDataAccessException(DataAccessMessages.FAILED_TO_FETCH_ALL_USERS));
			return optionalUsers.get();
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(exception.getMessage(), exception.getCause());
		}
	}

	@Override
	public Collection<User> findById(Collection<Long> identities, Boolean flag) {
		try {
			final Optional<Collection<User>> optionalUsers = null == flag ? userRepository.findByIdentity(identities)
					: userRepository.findByIdentity(flag, identities);
			optionalUsers.orElseThrow(
					() -> new OpenRetailsDataAccessException(DataAccessMessages.FAILED_TO_FETCH_USERS_BY_ID));
			return optionalUsers.get();
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(exception.getMessage(), exception.getCause());
		}

	}

	@Override
	public User findById(Long identity, Boolean flag) {
		try {
			final Optional<User> optionalUsers = null == flag ? userRepository.findByIdentity(identity)
					: userRepository.findByIdentity(flag, identity);
			optionalUsers.orElseThrow(
					() -> new OpenRetailsDataAccessException(DataAccessMessages.FAILED_TO_FETCH_USERS_BY_ID));
			return optionalUsers.get();
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(exception.getMessage(), exception.getCause());
		}
	}

	@Override
	public Collection<User> findByUser(Collection<String> users, Boolean flag) {

		try {
			final Collection<String> userCollection = users.stream().map(existingUser -> {
				return existingUser.trim().toLowerCase();
			}).collect(Collectors.toList());

			final Optional<Collection<User>> optionalUsers = null == flag
					? userRepository.findByUsernameOrPrimaryEmailId(userCollection, userCollection)
					: userRepository.findByUsernameOrPrimaryEmailIdAndObsolete(flag, userCollection, userCollection);
			optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(
					DataAccessMessages.FAILED_TO_FETCH_USERS_BY_USERNAME_OR_EMAIL));
			return optionalUsers.get();
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(exception.getMessage(), exception.getCause());
		}

	}

	@Override
	public User findByUser(String user, Boolean flag) {

		try {
			user = user.trim().toLowerCase();
			final Optional<User> optionalUsers = null == flag
					? userRepository.findByUsernameOrPrimaryEmailId(user, user)
					: userRepository.findByUsernameOrPrimaryEmailIdAndObsolete(flag, user, user);
			optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(
					DataAccessMessages.FAILED_TO_FETCH_USERS_BY_USERNAME_OR_EMAIL));
			return optionalUsers.get();
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(exception.getMessage(), exception.getCause());
		}

	}

	@Override
	public Collection<Long> findIdByUser(Collection<String> user, Boolean flag) {
		try {
			return findByUser(user, flag).stream().map(existingUser -> {
				return existingUser.getIdentity();
			}).collect(Collectors.toList());
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(exception.getMessage(), exception.getCause());
		}
	}

	@Override
	public Long findIdByUser(String user, Boolean flag) {
		try {
			return findByUser(user, flag).getIdentity();
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(exception.getMessage(), exception.getCause());
		}
	}

	@Override
	public Collection<String> findUsernameById(Collection<Long> identities, Boolean flag) {
		try {
			return findById(identities, flag).stream().map(existingUser -> {
				return existingUser.getUsername();
			}).collect(Collectors.toList());
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(exception.getMessage(), exception.getCause());
		}
	}

	@Override
	public String findUsernameById(Long identity, Boolean flag) {
		try {
			return findById(identity, flag).getUsername();
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(exception.getMessage(), exception.getCause());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Collection<User> update(Collection<User> users)
			throws OpenRetailsValidationException, OpenRetailsDataAccessException {
		try {
			return userRepository.save(users.stream().map(existingUser -> {
				return updateUserMapping(existingUser);
			}).collect(Collectors.toList()));
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(DataAccessMessages.FAILED_UPDATE_USERS + exception.getMessage(),
					exception.getCause());
		}
	}

	private User updateUserMapping(User existingUser) {
		final User dbUser = findById(existingUser.getIdentity(), null);
		dbUser.setAge(existingUser.getAge());
		dbUser.setComment(existingUser.getComment());
		dbUser.setEmailVerified(existingUser.isEmailVerified());
		dbUser.setFirstName(existingUser.getFirstName());
		dbUser.setGender(existingUser.getGender());
		dbUser.setLastName(existingUser.getLastName());
		dbUser.setMiddleName(existingUser.getMiddleName());
		dbUser.setMobileVerified(existingUser.isMobileVerified());
		dbUser.setPassword(passwordEncoder.encode(existingUser.getPassword()));
		dbUser.setPrimaryEmailId(existingUser.getPrimaryEmailId());
		dbUser.setPrimaryMobileNumber(existingUser.getPrimaryMobileNumber());
		dbUser.setUserType(existingUser.getUserType());
		dbUser.setUsername(existingUser.getUsername());
		dbUser.setObsolete(existingUser.isObsolete());
		final Address primaryAddress = dbUser.getPermanentAddress();
		primaryAddress.setAddressFreeText(existingUser.getPermanentAddress().getAddressFreeText());
		primaryAddress.setComment(existingUser.getPermanentAddress().getComment());
		primaryAddress.setObsolete(existingUser.getPermanentAddress().isObsolete());
		final Address secondaryAddress = dbUser.getSecondaryAddress();
		secondaryAddress.setAddressFreeText(existingUser.getSecondaryAddress().getAddressFreeText());
		secondaryAddress.setComment(existingUser.getSecondaryAddress().getComment());
		secondaryAddress.setObsolete(existingUser.getSecondaryAddress().isObsolete());
		return dbUser;
	}

}
