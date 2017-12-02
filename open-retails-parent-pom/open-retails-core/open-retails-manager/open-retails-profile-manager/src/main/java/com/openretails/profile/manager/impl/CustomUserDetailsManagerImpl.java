package com.openretails.profile.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openretails.profile.dao.UserDao;
import com.openretails.profile.manager.CustomUserDetailsManager;
import com.openretails.profile.model.CustomUserDetails;

@Service("customUserDetailsManager")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CustomUserDetailsManagerImpl implements CustomUserDetailsManager {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new CustomUserDetails(userDao.getActiveUserByUsernameOrPrimaryEmailId(username));
	}

}
