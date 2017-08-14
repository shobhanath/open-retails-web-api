package com.openretails.profile.manager.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import com.openretails.profile.manager.UserManager;
import com.openretails.profile.model.User;

public class AuditorAwareImpl implements AuditorAware<User> {

	@Autowired
	private UserManager userManager;

	@Override
	public User getCurrentAuditor() {
		return userManager.getCurrentUser();
	}
}
