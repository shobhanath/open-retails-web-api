package com.openretails.profile.dao.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.constant.ApplicationConstants;
import com.openretails.common.constant.DataAccessMessages;
import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsDataAccessException;
import com.openretails.common.utils.Base64;
import com.openretails.profile.dao.UserDao;
import com.openretails.profile.dao.properties.PropertyResourceConfig;
import com.openretails.profile.model.User;
import com.openretails.profile.repository.UserRepository;

@Repository(SpringBeanIds.USER_DAO)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PropertyResourceConfig propertyResource;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Collection<User> create(Collection<User> users) {
		try {
			return userRepository.save(encodePassword(users));
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(
					String.format(DataAccessMessages.FAILED_CREATE_USERS, exception.getMessage()),
					exception.getCause());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Collection<User> enableOrDisable(Collection<String> users, boolean isEnabled) {
		try {
			users = users.stream().map(user -> user.trim().toLowerCase()).collect(Collectors.toList());
			final Optional<Collection<User>> optionalUsers = userRepository.findByUsernameOrPrimaryEmailId(users,
					users);
			final Collection<User> userCollection = optionalUsers
					.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.USERS_NOT_FOUND)).stream()
					.map(existingUser -> {
						existingUser.setObsolete(isEnabled);
						return existingUser;
					}).collect(Collectors.toList());
			return userRepository.save(userCollection);
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(
					String.format(isEnabled ? DataAccessMessages.FAILED_TO_ENABLE_USERS
							: DataAccessMessages.FAILED_TO_DISABLE_USERS, exception.getMessage()),
					exception.getCause());
		}
	}

	private Collection<User> encodePassword(Collection<User> users) {
		return users.stream().map(existingUser -> {
			existingUser.setPassword(Base64.encode(propertyResource.getDbSalt() + existingUser.getPassword()));
			return existingUser;
		}).collect(Collectors.toList());
	}

	@Override
	public Collection<User> findAll(Boolean flag) {
		final Optional<Collection<User>> optionalUsers = null == flag ? userRepository.getAll()
				: userRepository.findAll(flag);
		return optionalUsers
				.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.FAILED_TO_FETCH_ALL_USERS));
	}

	@Override
	public Collection<User> findById(Collection<Long> identities, Boolean flag) {
		final Optional<Collection<User>> optionalUsers = null == flag ? userRepository.findByIdentity(identities)
				: userRepository.findByIdentity(flag, identities);
		return optionalUsers
				.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.FAILED_TO_FETCH_USERS_BY_ID));
	}

	@Override
	public User findById(Long identity, Boolean flag) {
		final Optional<User> optionalUsers = null == flag ? userRepository.findByIdentity(identity)
				: userRepository.findByIdentity(flag, identity);
		return optionalUsers
				.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.FAILED_TO_FETCH_USERS_BY_ID));
	}

	@Override
	public Collection<User> findByUser(Collection<String> users, Boolean flag) {

		final Collection<String> userCollection = users.stream().map(existingUser -> existingUser.trim().toLowerCase())
				.collect(Collectors.toList());

		final Optional<Collection<User>> optionalUsers = null == flag
				? userRepository.findByUsernameOrPrimaryEmailId(userCollection, userCollection)
				: userRepository.findByUsernameOrPrimaryEmailIdAndObsolete(flag, userCollection, userCollection);
		return optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(
				DataAccessMessages.FAILED_TO_FETCH_USERS_BY_USERNAME_OR_EMAIL));

	}

	@Override
	public User findByUser(final String user, Boolean flag) {
		final String userLower = user.trim().toLowerCase();
		final Optional<User> optionalUser = null == flag
				? userRepository.findByUsernameOrPrimaryEmailId(userLower, userLower)
				: userRepository.findByUsernameOrPrimaryEmailIdAndObsolete(flag, userLower, userLower);
		return optionalUser.orElseThrow(() -> new OpenRetailsDataAccessException(
				new StringBuilder(DataAccessMessages.FAILED_TO_FETCH_USERS_BY_USERNAME_OR_EMAIL)
						.append(ApplicationConstants.COLON)
						.append(user)
						.toString()));

	}

	@Override
	public User findByUsernameOrPrimaryEmailIdAndPasswordAndObsoleteTrue(String username, String emailId,
			String password) {
		final Optional<User> optionalUser = userRepository.findByUsernameOrPrimaryEmailIdAndPasswordAndObsoleteTrue(
				username, emailId, Base64.encode(propertyResource.getDbSalt() + password));
		return optionalUser.orElseThrow(
				() -> new OpenRetailsDataAccessException(DataAccessMessages.FAILED_TO_FETCH_BY_USERNAME_AND_PASSWORD));
	}

	@Override
	public Collection<Long> findIdByUser(Collection<String> user, Boolean flag) {
		return findByUser(user, flag).stream().map(User::getIdentity)
				.collect(Collectors.toList());
	}

	@Override
	public Long findIdByUser(String user, Boolean flag) {
		return findByUser(user, flag).getIdentity();
	}

	@Override
	public Collection<String> findUsernameById(Collection<Long> identities, Boolean flag) {
		return findById(identities, flag).stream().map(User::getUsername)
				.collect(Collectors.toList());
	}

	@Override
	public String findUsernameById(Long identity, Boolean flag) {
		return findById(identity, flag).getUsername();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Collection<User> update(Collection<User> users) {
		try {
			return userRepository.save(users.stream().map(existingUser -> {
				final User dbUser = findById(existingUser.getIdentity(), null);
				existingUser.setPassword(dbUser.getPassword());
				return existingUser;
			}).collect(Collectors.toList()));
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(DataAccessMessages.FAILED_UPDATE_USERS + exception.getMessage(),
					exception.getCause());
		}
	}

}
