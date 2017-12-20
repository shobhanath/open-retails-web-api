package com.openretails.profile.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.profile.dao.UserDao;
import com.openretails.profile.manager.CustomUserDetailsManager;
import com.openretails.profile.model.CustomUserDetails;

@Service(SpringBeanIds.CUSTOM_USER_DETAILS_MANAGER)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CustomUserDetailsManagerImpl implements CustomUserDetailsManager {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return new CustomUserDetails(userDao.findByUser(username, true));
	}

}
