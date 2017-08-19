package com.openretails.profile.manager;

import java.util.Collection;

import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.data.Response;
import com.openretails.data.ResponseCollection;
import com.openretails.data.UserDTO;
import com.openretails.profile.model.User;

public interface UserManager {

	ResponseCollection<UserDTO> create(Collection<UserDTO> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	Response disable(Collection<String> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	ResponseCollection<UserDTO> findAll() throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	UserDTO getActiveUserByUsernameOrPrimaryEmailId(String user)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	User getCurrentUser();

	void setCurrentUser(Long userId);

    Response update(Collection<User> users) throws OpenRetailsValidationException, OpenRetailsRuntimeException;
}
