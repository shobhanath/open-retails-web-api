package com.openretails.profile.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Collection<User> create(Collection<User> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		final List<User> concurrentList = new CopyOnWriteArrayList<>();
		concurrentList.addAll(users);
		for (final User user : concurrentList) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		return userRepository.save(concurrentList);
	}

	@Override
	public Collection<User> disable(Collection<User> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		return create(users);
	}

	@Override
	public Collection<User> findAll(Collection<User> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		return userRepository.findAll();
	}

	@Override
	public User getActiveUserById(Long identity) {
		return userRepository.findByIdentityAndObsoleteTrue(identity);
	}

	@Override
	public Collection<User> update(Collection<User> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		return create(users);
	}

}
