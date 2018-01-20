package com.openretails.profile.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.constant.DataAccessMessages;
import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsDataAccessException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.common.utils.Base64;
import com.openretails.profile.dao.UserDao;
import com.openretails.profile.dao.properties.PropertyResourceConfig;
import com.openretails.profile.model.Address;
import com.openretails.profile.model.User;
import com.openretails.profile.repository.AddressRepository;
import com.openretails.profile.repository.UserRepository;

@Repository(SpringBeanIds.USER_DAO)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PropertyResourceConfig propertyResource;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Collection<User> create(Collection<User> users) {
		try {
			final List<String> existingUserList = exists(users);

			if (!existingUserList.isEmpty()) {
				throw new OpenRetailsValidationException(
						String.format(DataAccessMessages.USER_EXIST_BY_EMAIL, existingUserList));
			}
			return users.stream().map(user -> {
				final Address primaryAddress = user.getPermanentAddress();
				if (primaryAddress != null
						&& (primaryAddress.getIdentity() == null || primaryAddress.getIdentity() == 0)) {
					user.setPermanentAddress(addressRepository.save(user.getPermanentAddress()));
				} else {
					user.setPermanentAddress(null);
				}
				final Address secondaryAddress = user.getSecondaryAddress();
				if (secondaryAddress != null
						&& (secondaryAddress.getIdentity() == null || secondaryAddress.getIdentity() == 0)) {
					user.setSecondaryAddress(addressRepository.save(user.getSecondaryAddress()));
				} else {
					user.setSecondaryAddress(null);
				}
				user.setPassword(Base64.encode(propertyResource.getDbSalt() + user.getPassword()));
				return userRepository.save(user);
			}).collect(Collectors.toList());

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
			final Collection<User> userCollection = findByUser(users, null).stream().map(existingUser -> {
				existingUser.setObsolete(isEnabled);
				return existingUser;
			}).collect(Collectors.toList());
			return userRepository.save(userCollection);
		} catch (final Exception exception) {
			final String exceptionMessage = isEnabled ? DataAccessMessages.FAILED_TO_ENABLE_USERS
					: DataAccessMessages.FAILED_TO_DISABLE_USERS;
			throw new OpenRetailsDataAccessException(String.format(exceptionMessage, exception.getMessage()),
					exception.getCause());
		}
	}

	private List<String> exists(Collection<User> users) {
		final List<String> existingUserList = new ArrayList<>();
		users.stream().forEach(user -> {
			final Optional<User> existingUser = userRepository.findByUsernameOrPrimaryEmailId(
					user.getUsername().trim().toLowerCase(), user.getPrimaryEmailId().trim().toLowerCase());
			if (existingUser.isPresent()) {
				existingUserList.add(existingUser.get().getPrimaryEmailId());
			}
		});
		return existingUserList;
	}

	@Override
	public Collection<User> findAll(Boolean flag) {
		final Optional<Collection<User>> optionalUsers = null == flag ? userRepository.getAll()
				: userRepository.findAll(flag);
		return optionalUsers.filter(users -> !users.isEmpty())
				.orElseThrow(() -> new OpenRetailsDataAccessException(DataAccessMessages.FAILED_TO_FETCH_ALL_USERS));
	}

	@Override
	public Collection<User> findById(Collection<Long> identities, Boolean flag) {
		final List<Long> failedIds = new ArrayList<>();
		final List<User> existingUsers = new ArrayList<>();
		identities.stream().forEach(identity -> {
			final Optional<User> existingUser = null == flag ? userRepository.findByIdentity(identity)
					: userRepository.findByIdentity(flag, identity);
			if (existingUser.isPresent()) {
				existingUsers.add(existingUser.get());
			} else {
				failedIds.add(identity);
			}

		});
		if (!failedIds.isEmpty()) {
			throw new OpenRetailsDataAccessException(
					String.format(DataAccessMessages.FAILED_TO_FETCH_USERS_BY_IDS, failedIds));
		}
		return existingUsers;
	}


	@Override
	public User findById(Long identity, Boolean flag) {
		final Optional<User> optionalUsers = null == flag ? userRepository.findByIdentity(identity)
				: userRepository.findByIdentity(flag, identity);
		return optionalUsers.orElseThrow(() -> new OpenRetailsDataAccessException(
				String.format(DataAccessMessages.FAILED_TO_FETCH_USERS_BY_ID, identity)));
	}

	@Override
	public Collection<User> findByUser(Collection<String> users, Boolean flag) {
		final List<String> errorMsg = new ArrayList<>();
		final List<User> existingUsers = new ArrayList<>();
		users.stream().forEach(user -> {
			final String userNameOrEmailId = user.trim().toLowerCase();
			final Optional<User> existingUser = null == flag
					? userRepository.findByUsernameOrPrimaryEmailId(userNameOrEmailId, userNameOrEmailId)
					: userRepository.findByUsernameOrPrimaryEmailIdAndObsolete(flag, userNameOrEmailId,
							userNameOrEmailId);
			if (existingUser.isPresent()) {
				existingUsers.add(existingUser.get());
			} else {
				errorMsg.add(user);
			}

		});
		if (!errorMsg.isEmpty()) {
			throw new OpenRetailsDataAccessException(
					String.format(DataAccessMessages.FAILED_TO_FETCH_USERS_BY_USERNAME_OR_EMAIL, errorMsg));
		}
		return existingUsers;
	}

	@Override
	public User findByUser(final String user, Boolean flag) {
		final String userLower = user.trim().toLowerCase();
		final Optional<User> optionalUser = null == flag
				? userRepository.findByUsernameOrPrimaryEmailId(userLower, userLower)
				: userRepository.findByUsernameOrPrimaryEmailIdAndObsolete(flag, userLower, userLower);
		return optionalUser.orElseThrow(() -> new OpenRetailsDataAccessException(
				String.format(DataAccessMessages.FAILED_TO_FETCH_USERS_BY_USERNAME_OR_EMAIL, user)));

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
		return findByUser(user, flag).stream().map(User::getIdentity).collect(Collectors.toList());
	}

	@Override
	public Long findIdByUser(String user, Boolean flag) {
		return findByUser(user, flag).getIdentity();
	}

	@Override
	public Collection<String> findUsernameById(Collection<Long> identities, Boolean flag) {
		return findById(identities, flag).stream().map(User::getUsername).collect(Collectors.toList());
	}

	@Override
	public String findUsernameById(Long identity, Boolean flag) {
		return findById(identity, flag).getUsername();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Collection<User> update(Collection<User> users) {
		try {
			findById(users.stream().map(User::getIdentity).collect(Collectors.toList()), null);
			return userRepository.save(users.stream().map(existingUser -> {
				final User dbUser = findById(existingUser.getIdentity(), null);
				existingUser.setPassword(dbUser.getPassword());
				return existingUser;
			}).collect(Collectors.toList()));
		} catch (final Exception exception) {
			throw new OpenRetailsDataAccessException(
					String.format(DataAccessMessages.FAILED_UPDATE_USERS, exception.getMessage()),
					exception.getCause());
		}
	}

}
