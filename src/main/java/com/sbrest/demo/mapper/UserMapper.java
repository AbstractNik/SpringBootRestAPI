package com.sbrest.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



import com.sbrest.demo.dto.UserMsDto;
import com.sbrest.demo.entities.User;



@Mapper(componentModel="spring")

public interface UserMapper {
	
	UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
	
	@Mapping(source="email",target="emailAddress")
	UserMsDto userToUserMsDTO(User user);
	
	
	List<UserMsDto> userListToUserMsDTOList(List<User> user);
	
	
	
	

}
