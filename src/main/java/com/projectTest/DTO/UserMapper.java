package com.projectTest.DTO;

import org.mapstruct.Mapper;

import com.projectTest.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO userToUserDTO(User user);
	
	User userDtoToUser(UserDTO userDto);
}
