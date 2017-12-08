package com.openretails.profile.dao;

import java.util.Collection;

import com.openretails.profile.model.User;

public interface UserDao {

	Collection<User> create(Collection<User> users);

	Collection<User> enableOrDisable(Collection<String> users, boolean isEnabled);

	Collection<User> findAll(Boolean flag);

	Collection<User> findById(Collection<Long> identity, Boolean flag);

	User findById(Long identity, Boolean flag);

	Collection<User> findByUser(Collection<String> user, Boolean flag);

	User findByUser(String user, Boolean flag);

	Collection<Long> findIdByUser(Collection<String> user, Boolean flag);

	Long findIdByUser(String user, Boolean flag);

	Collection<String> findUsernameById(Collection<Long> identity, Boolean flag);

	String findUsernameById(Long identity, Boolean flag);

	Collection<User> update(Collection<User> users);

}
