package com.openretails.profile.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsBusinessException;
import com.openretails.data.Collections;
import com.openretails.data.UserDTO;
import com.openretails.profile.dao.UserDao;
import com.openretails.profile.manager.UserManager;
import com.openretails.profile.manager.mapper.UserMapper;
import com.openretails.profile.manager.validator.UserValidator;
import com.openretails.profile.model.User;

@Service(SpringBeanIds.USER_MANAGER)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserMapper userMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Collections<UserDTO> create(Collections<UserDTO> userDTOs) {
		try {
			UserValidator.fullValidate(userDTOs.getCollectionObj());
			return new Collections<>(
					userMapper.mapUserDTO(userDao.create(userMapper.mapUser(userDTOs.getCollectionObj()))));

		} catch (final Exception e) {
			throw new OpenRetailsBusinessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collections<UserDTO> enableOrDisable(Collections<String> users, boolean isEnabled) {
		UserValidator.validateEmailAddress(users.getCollectionObj());
		return new Collections<>(userMapper.mapUserDTO(userDao.enableOrDisable(users.getCollectionObj(), isEnabled)));
	}

	@Override
	public Collections<UserDTO> findAll(Boolean flag) {
		return new Collections<>(userMapper.mapUserDTO(userDao.findAll(flag)));
	}

	@Override
	public Collections<UserDTO> findById(Collections<Long> identities, Boolean flag) {
		return new Collections<>(userMapper.mapUserDTO(userDao.findById(identities.getCollectionObj(), flag)));
	}

	@Override
	public UserDTO findById(Long identity, Boolean flag) {
		UserValidator.validateId(identity);
		return userMapper.map(userDao.findById(identity, flag));
	}

	@Override
	public Collections<UserDTO> findByUser(Collections<String> users, Boolean flag) {
		UserValidator.validateEmailAddress(users.getCollectionObj());
		return new Collections<>(userMapper.mapUserDTO(userDao.findByUser(users.getCollectionObj(), flag)));
	}

	@Override
	public UserDTO findByUser(String user, Boolean flag) {
		UserValidator.validateEmailAddress(user);
		return userMapper.map(userDao.findByUser(user, flag));
	}

	@Override
	public Collections<Long> findIdByUser(Collections<String> users, Boolean flag) {
		UserValidator.validateEmailAddress(users.getCollectionObj());
		return new Collections<>(userDao.findIdByUser(users.getCollectionObj(), flag));
	}

	@Override
	public Long findIdByUser(String user, Boolean flag) {
		UserValidator.validateEmailAddress(user);
		return userDao.findIdByUser(user, flag);
	}

	@Override
	public Collections<String> findUsernameById(Collections<Long> identities, Boolean flag) {
		UserValidator.validateId(identities.getCollectionObj());
		return new Collections<>(userDao.findUsernameById(identities.getCollectionObj(), flag));
	}

	@Override
	public String findUsernameById(Long identity, Boolean flag) {
		UserValidator.validateId(identity);
		return userDao.findUsernameById(identity, flag);
	}

	@Override
	public User getCurrentUser() {
		return null;
	}

	@Override
	public Collections<UserDTO> update(Collections<UserDTO> users) {
		return new Collections<>(userMapper.mapUserDTO(userDao.update(userMapper.mapUser(users.getCollectionObj()))));
	}

}
