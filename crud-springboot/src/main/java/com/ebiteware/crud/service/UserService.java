package com.ebiteware.crud.service;

import java.util.List;

import com.ebiteware.crud.dto.CreateUserDto;
import com.ebiteware.crud.dto.ServiceResponse;
import com.ebiteware.crud.dto.UpdateUserDto;
import com.ebiteware.crud.dto.UserResponseDto;

/**
 * UserService interface defines the contract for user-related operations.
 * It provides methods to create, retrieve(get), update(patch), and delete users.
 */
public interface UserService {
    
    public ServiceResponse<UserResponseDto> createUser(CreateUserDto user);
    public ServiceResponse<UserResponseDto> getUser(String id);
    public ServiceResponse<List<UserResponseDto>> getUsers();
    public ServiceResponse<UserResponseDto> updateUser(String id, UpdateUserDto user);
    public ServiceResponse<Boolean> deleteUser(String id);
}
