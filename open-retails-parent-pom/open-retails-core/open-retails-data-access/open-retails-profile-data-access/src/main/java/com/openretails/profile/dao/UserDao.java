package com.openretails.profile.dao;

import java.util.Collection;

import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.profile.model.User;

public interface UserDao {

	Collection<User> create(Collection<User> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	Collection<User> disable(Collection<String> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	Collection<User> enable(Collection<String> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	Collection<User> findAll() throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	User getActiveUserById(Long identity);

	User getActiveUserByUsernameOrPrimaryEmailId(String user);

	Collection<User> update(Collection<User> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException;
}
