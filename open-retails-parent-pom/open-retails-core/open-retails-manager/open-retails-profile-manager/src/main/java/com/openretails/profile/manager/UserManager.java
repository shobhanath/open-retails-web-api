package com.openretails.profile.manager;

import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.data.Collections;
import com.openretails.data.UserDTO;
import com.openretails.profile.model.User;

public interface UserManager {

	Collections<UserDTO> create(Collections<UserDTO> users)
			throws OpenRetailsValidationException, OpenRetailsRuntimeException;

	User getCurrentUser();

}
