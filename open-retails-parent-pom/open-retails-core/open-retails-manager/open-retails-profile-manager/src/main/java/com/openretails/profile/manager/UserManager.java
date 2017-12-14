package com.openretails.profile.manager;

import com.openretails.data.Collections;
import com.openretails.data.Single;
import com.openretails.data.UserDTO;
import com.openretails.profile.model.User;

public interface UserManager {

	Collections<UserDTO> create(Collections<UserDTO> users);

	Collections<UserDTO> enableOrDisable(Collections<String> users, boolean isEnabled);

	Collections<UserDTO> findAll(Boolean flag);

	Collections<UserDTO> findById(Collections<Long> identity, Boolean flag);

	UserDTO findById(Long identity, Boolean flag);

	Collections<UserDTO> findByUser(Collections<String> user, Boolean flag);

	UserDTO findByUser(String user, Boolean flag);

	Collections<Long> findIdByUser(Collections<String> user, Boolean flag);

	Single<Long> findIdByUser(String user, Boolean flag);

	Collections<String> findUsernameById(Collections<Long> identity, Boolean flag);

	Single<String> findUsernameById(Long identity, Boolean flag);

	User getCurrentUser();

	Collections<UserDTO> partialUpdate(Collections<UserDTO> users);

	Collections<UserDTO> update(Collections<UserDTO> users);

}
