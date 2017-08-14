package com.openretails.profile.manager;

import java.util.Collection;
import java.util.List;

import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.data.Response;
import com.openretails.data.ResponseCollection;
import com.openretails.data.UserDTO;
import com.openretails.profile.model.User;

public interface UserManager {

	ResponseCollection<UserDTO> create(Collection<UserDTO> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	Response disable(Collection<User> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	List<User> findAll(Collection<User> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	User getCurrentUser();

	void setCurrentUser(Long userId);

    Response update(Collection<User> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException;
}