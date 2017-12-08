package com.openretails.profile.manager.mapper;

import java.util.ArrayList;
import java.util.Collection;

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

	public Collection<User> mapUser(Collection<UserDTO> userDTO) {
		final Collection<User> userCollection = new ArrayList<>();
		for (final UserDTO u : userDTO) {
			userCollection.add(map(u));
		}
		return userCollection;
	}

	public Collection<UserDTO> mapUserDTO(Collection<User> user) {
		final Collection<UserDTO> userDTOCollection = new ArrayList<>();
		for(final User u : user){
			userDTOCollection.add(map(u));
		}
		return userDTOCollection;
	}
}
