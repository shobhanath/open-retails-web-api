package com.openretails.profile.manager.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openretails.data.UserDTO;
import com.openretails.profile.model.User;
import com.openretails.profile.repository.UserTypeRepository;

@Component("userMapper")
public class UserMapper {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserTypeRepository userTypeRepository;

	public UserDTO map(User user) {
		final UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		userDTO.setUserTypeId(user.getUserType().getIdentity());
		return userDTO;
	}

	public User map(UserDTO userDTO) {
		final User user = modelMapper.map(userDTO, User.class);
		user.setUserType(userTypeRepository.findByIdentityAndObsoleteTrue(userDTO.getUserTypeId()));
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
