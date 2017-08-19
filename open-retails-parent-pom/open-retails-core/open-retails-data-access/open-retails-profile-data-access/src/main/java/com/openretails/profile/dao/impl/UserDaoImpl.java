package com.openretails.profile.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.profile.dao.UserDao;
import com.openretails.profile.model.User;
import com.openretails.profile.repository.UserRepository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Collection<User> create(Collection<User> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		try {
			final List<User> concurrentList = new CopyOnWriteArrayList<>();
			concurrentList.addAll(users);
			for (final User user : concurrentList) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			return userRepository.save(concurrentList);
		} catch (final Exception e) {
			log.error("Failed to create users : ", e.getCause());
			throw new OpenRetailsRuntimeException("Failed to create users : " + e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collection<User> disable(Collection<String> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		try {
			final List<User> userList = new ArrayList<>();
			for (final String emailAddress : users) {
				final User user = getActiveUserByUsernameOrPrimaryEmailId(emailAddress);
				user.setObsolete(false);
				userList.add(user);
			}
			return userRepository.save(userList);
		} catch (final Exception e) {
			log.error("Failed to disable list of users : ", e.getCause());
			throw new OpenRetailsRuntimeException("Failed to disable list of users : " + e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collection<User> enable(Collection<String> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		try {
			final List<User> userList = new ArrayList<>();
			for (final String emailAddress : users) {
				final User user = getActiveUserByUsernameOrPrimaryEmailId(emailAddress);
				user.setObsolete(true);
				userList.add(user);
			}
			return userRepository.save(userList);
		} catch (final Exception e) {
			log.error("Failed to enable list of users : ", e.getCause());
			throw new OpenRetailsRuntimeException("Failed to enable list of users : " + e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collection<User> findAll() throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		return userRepository.findAllObsoleteTrue();
	}

	@Override
	public User getActiveUserById(Long identity) {
		return userRepository.findByIdentityAndObsoleteTrue(identity);
	}

	@Override
	public User getActiveUserByUsernameOrPrimaryEmailId(String user) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameOrPrimaryEmailIdAndObsoleteTrue(user, user);
	}

	@Override
	public Collection<User> update(Collection<User> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		return create(users);
	}

}
