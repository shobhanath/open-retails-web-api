package com.openretails.profile.manager.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.data.UserDTO;
import com.openretails.profile.model.User;

@Component(SpringBeanIds.USER_MAPPER)
public class UserMapper {
	@Autowired
	private ModelMapper modelMapper;


	public UserDTO map(User user) {
		final UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		userDTO.setPassword(null);
		return userDTO;
	}

	public User map(UserDTO userDTO) {
		final User user = modelMapper.map(userDTO, User.class);
		return user;
	}

	public Collection<User> mapUser(Collection<UserDTO> usersDTO) {
		return usersDTO.stream().map(user -> {
			return map(user);
		}).collect(Collectors.toList());
	}

	public Collection<UserDTO> mapUserDTO(Collection<User> users) {
		return users.stream().map(user -> {
			return map(user);
		}).collect(Collectors.toList());
	}

}
