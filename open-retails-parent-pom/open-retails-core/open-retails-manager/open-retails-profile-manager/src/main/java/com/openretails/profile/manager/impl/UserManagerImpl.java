package com.openretails.profile.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.constant.SpringBeanIds;
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
	public Collections<UserDTO> create(Collections<UserDTO> userDTO) {
		return new Collections<UserDTO>(
				userMapper.mapUserDTO(userDao.create(userMapper.mapUser(userDTO.getCollectionObj()))));
	}

	@Override
	public Collections<UserDTO> enableOrDisable(Collections<String> users, boolean isEnabled) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collections<UserDTO> findAll(Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collections<UserDTO> findById(Collections<Long> identity, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findById(Long identity, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collections<UserDTO> findByUser(Collections<String> user, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findByUser(String user, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collections<Long> findIdByUser(Collections<String> user, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findIdByUser(String user, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collections<String> findUsernameById(Collections<Long> identity, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findUsernameById(Long identity, Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collections<UserDTO> update(Collections<UserDTO> users) {
		// TODO Auto-generated method stub
		return null;
	}


}
