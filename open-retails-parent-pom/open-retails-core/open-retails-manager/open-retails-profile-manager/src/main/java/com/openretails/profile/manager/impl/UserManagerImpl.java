package com.openretails.profile.manager.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.data.Response;
import com.openretails.data.ResponseCollection;
import com.openretails.data.UserDTO;
import com.openretails.profile.dao.UserDao;
import com.openretails.profile.manager.UserManager;
import com.openretails.profile.manager.mapper.UserMapper;
import com.openretails.profile.model.User;

@Service("userManager")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserManagerImpl implements UserManager {

	private Long userId = null;
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserMapper userMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public ResponseCollection<UserDTO> create(Collection<UserDTO> userDTO)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		return new ResponseCollection<UserDTO>(userMapper.mapUserDTO(userDao.create(userMapper.mapUser(userDTO))));
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Response disable(Collection<User> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseCollection<UserDTO> findAll() throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		return new ResponseCollection<UserDTO>(userMapper.mapUserDTO(userDao.findAll()));
	}

	@Override
	public User getCurrentUser() {
		return userDao.getActiveUserById(userId);
	}

	@Override
	public void setCurrentUser(Long userId) {
		this.userId = userId;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Response update(Collection<User> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
