package com.openretails.profile.manager.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openretails.common.constant.BusinessMessages;
import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.data.Collections;
import com.openretails.data.Single;
import com.openretails.data.UserDTO;
import com.openretails.profile.dao.UserDao;
import com.openretails.profile.manager.UserManager;
import com.openretails.profile.manager.mapper.UserMapper;
import com.openretails.profile.manager.validator.UserValidator;
import com.openretails.profile.model.User;

@Service(SpringBeanIds.USER_MANAGER)
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserMapper userMapper;

	@Override
	public Collections<UserDTO> create(Collections<UserDTO> userDTOs) {
		return new Collections<>(userMapper.mapDTO(userDao.create(userMapper.mapEntity(userDTOs.getCollection()))));
	}

	@Override
	public Collections<UserDTO> enableOrDisable(Collections<String> users, boolean isEnabled) {
		UserValidator.validateEmailAddress(users.getCollection());
		return new Collections<>(userMapper.mapDTO(userDao.enableOrDisable(users.getCollection(), isEnabled)));
	}

	@Override
	public Collections<UserDTO> findAll(Boolean flag) {
		return new Collections<>(userMapper.mapDTO(userDao.findAll(flag)));
	}

	@Override
	public Collections<UserDTO> findById(Collections<Long> identities, Boolean flag) {
		return new Collections<>(userMapper.mapDTO(userDao.findById(identities.getCollection(), flag)));
	}

	@Override
	public UserDTO findById(Long identity, Boolean flag) {
		UserValidator.validateId(identity);
		return userMapper.map(userDao.findById(identity, flag));
	}

	@Override
	public Collections<UserDTO> findByUser(Collections<String> users, Boolean flag) {
		UserValidator.validateEmailAddress(users.getCollection());
		return new Collections<>(userMapper.mapDTO(userDao.findByUser(users.getCollection(), flag)));
	}

	@Override
	public UserDTO findByUser(String user, Boolean flag) {
		UserValidator.validateEmailAddress(user);
		return userMapper.map(userDao.findByUser(user, flag));
	}

	@Override
	public Collections<Long> findIdByUser(Collections<String> users, Boolean flag) {
		UserValidator.validateEmailAddress(users.getCollection());
		return new Collections<>(userDao.findIdByUser(users.getCollection(), flag));
	}

	@Override
	public Single<Long> findIdByUser(String user, Boolean flag) {
		UserValidator.validateEmailAddress(user);
		return new Single<>(userDao.findIdByUser(user, flag));
	}

	@Override
	public Collections<String> findUsernameById(Collections<Long> identities, Boolean flag) {
		UserValidator.validateId(identities.getCollection());
		return new Collections<>(userDao.findUsernameById(identities.getCollection(), flag));
	}

	@Override
	public Single<String> findUsernameById(Long identity, Boolean flag) {
		UserValidator.validateId(identity);
		return new Single<>(userDao.findUsernameById(identity, flag));
	}

	@Override
	public User getCurrentUser() {
		return null;
	}

	@Override
	public Collections<UserDTO> partialUpdate(Collections<UserDTO> users) {
		UserValidator.partialValidate(users.getCollection());
		return new Collections<>(userMapper.mapDTO(userDao.update(userMapper.mapEntity(users.getCollection()))));
	}

	@Override
	public Collections<UserDTO> update(Collections<UserDTO> users) {
		UserValidator.fullValidate(users.getCollection(), true);
		return new Collections<>(userMapper.mapDTO(userDao.update(userMapper.mapEntity(users.getCollection()))));
	}

	@Override
	public UserDTO validate(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			throw new OpenRetailsValidationException(BusinessMessages.VALIDATE_USER_PASSWORD);
		}
		return userMapper
				.map(userDao.findByUsernameOrPrimaryEmailIdAndPasswordAndObsoleteTrue(username, username, password));
	}

}
