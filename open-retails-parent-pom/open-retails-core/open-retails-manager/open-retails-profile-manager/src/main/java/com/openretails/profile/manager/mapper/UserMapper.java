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
		userDTO.setPrimaryMobileNumber(String.valueOf(user.getPrimaryMobileNumber()));
		return userDTO;
	}

	public User map(UserDTO userDTO) {
		userDTO.setUsername(userDTO.getUsername().trim().toLowerCase());
		userDTO.setPrimaryEmailId(userDTO.getPrimaryEmailId().toLowerCase());
		return modelMapper.map(userDTO, User.class);
	}

	public Collection<UserDTO> mapDTO(Collection<User> users) {
		return users.stream().map(user -> map(user)).collect(Collectors.toList());
	}

	public Collection<User> mapEntity(Collection<UserDTO> usersDTO) {
		return usersDTO.stream().map(user -> map(user)).collect(Collectors.toList());
	}

}
