package com.openretails.profile.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.data.Collections;
import com.openretails.data.UserDTO;
import com.openretails.profile.dao.UserDao;
import com.openretails.profile.manager.UserManager;
import com.openretails.profile.manager.mapper.UserMapper;
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
	public Collections<UserDTO> create(Collections<UserDTO> userDTO)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException {
		return new Collections<UserDTO>(
				userMapper.mapUserDTO(userDao.create(userMapper.mapUser(userDTO.getCollectionObj()))));
	}

	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}


}
