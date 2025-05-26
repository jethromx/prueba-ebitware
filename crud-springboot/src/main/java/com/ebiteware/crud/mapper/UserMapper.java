package com.ebiteware.crud.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ebiteware.crud.dto.CreateUserDto;
import com.ebiteware.crud.dto.UserResponseDto;
import com.ebiteware.crud.entity.User;

public class UserMapper {

    // Private constructor to prevent instantiation
    private UserMapper() {
        throw new IllegalStateException("Cannot instantiate a constant class.");
    }


    // Converts a User entity to a UserResponseDto
    public static UserResponseDto toUserResponseDto(User user) {
        if (user == null) {
            return null;
        }
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }


    // Converts a CreateUserDto to a User entity
    // We can use a utility like MapStruct for more complex mappings, but for simplicity, we do it manually here.
    public static User toUser(CreateUserDto createUserDto) {
        if (createUserDto == null) {
            return null;
        }
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setRole(createUserDto.getRole());
        user.setStatus(createUserDto.getStatus());
        user.setPassword(createUserDto.getPassword());
        // Set other fields as necessary
        return user;
    }

    // Converts a list of User entities to a list of UserResponseDto
    public static List<UserResponseDto> toUserResponseDtoList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return null;
        }
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            userResponseDtos.add(toUserResponseDto(user));
        }
        return userResponseDtos;
    }


    // Converts a list of CreateUserDto to a list of User entities
    public static List<User> toUserList(List<CreateUserDto> createUserDtos) {
        if (createUserDtos == null || createUserDtos.isEmpty()) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (CreateUserDto createUserDto : createUserDtos) {
            users.add(toUser(createUserDto));
        }
        return users;
    }
    
}
