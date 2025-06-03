package com.ebiteware.crud.service.impl;

import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ebiteware.crud.config.Constants;
import com.ebiteware.crud.dto.CreateUserDto;
import com.ebiteware.crud.dto.ServiceResponse;
import com.ebiteware.crud.dto.UpdateUserDto;
import com.ebiteware.crud.dto.UserResponseDto;
import com.ebiteware.crud.entity.User;
import com.ebiteware.crud.enums.Status;
import com.ebiteware.crud.mapper.UserMapper;
import com.ebiteware.crud.repository.UserRepository;
import com.ebiteware.crud.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final String LOGLINE = "UserServiceImpl {} - {}";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository; // Assuming you have a UserRepository for database operations
   

    //  injecting the UserRepository through constructor injection, its better for testing  and recommended 
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ServiceResponse<UserResponseDto> createUser(CreateUserDto user) {
        
        LOGGER.info(LOGLINE, Constants.METHOD_CREATE, Constants.IN);
        // Convert CreateUserDto to User entity
        try {
            // Validate the input
            if (user == null) {
                return new ServiceResponse<UserResponseDto>(Status.KO, null, "User data is null", HttpStatus.BAD_REQUEST);
            }
            if (user.getName() == null || user.getEmail() == null) {
                return new ServiceResponse<UserResponseDto>(Status.KO, null, "Name and email are required", HttpStatus.BAD_REQUEST);
            }

           // Check if the user already exists
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return new ServiceResponse<UserResponseDto>(Status.KO, null, "User already exists", HttpStatus.BAD_REQUEST);
            }


            User userEntity = UserMapper.toUser(user);
            userEntity.setCreatedAt(new Date());
            userEntity.setUpdatedAt(new Date());
            userRepository.save(userEntity);
            
            // Convert the saved User entity back to UserResponseDto
            UserResponseDto userResponseDto = UserMapper.toUserResponseDto(userEntity);
            
             LOGGER.info(LOGLINE, Constants.METHOD_CREATE, Constants.OUT);
            // Return the response
            return new ServiceResponse<UserResponseDto>(Status.OK, userResponseDto, "User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error(LOGLINE, Constants.METHOD_CREATE, "Error : {}", e);
            return new ServiceResponse<UserResponseDto>(Status.KO, null, "Error validating user data", HttpStatus.INTERNAL_SERVER_ERROR);
        }

       
    }

    @Override
    public ServiceResponse<UserResponseDto> getUser(String id) {
        LOGGER.info(LOGLINE, Constants.METHOD_GET, Constants.IN);
        try {
            // Validate the input
            if (id == null) {
                return new ServiceResponse<UserResponseDto>(Status.KO, null, "User ID is required", HttpStatus.BAD_REQUEST);
            }

            User userEntity = userRepository.findById(id).orElse(null);
            if (userEntity == null) {
                return new ServiceResponse<UserResponseDto>(Status.KO, null, "User not found", HttpStatus.NOT_FOUND);
            }

            // Convert the User entity to UserResponseDto
            UserResponseDto userResponseDto = UserMapper.toUserResponseDto(userEntity);
            
             LOGGER.info(LOGLINE, Constants.METHOD_GET, Constants.OUT);
            // Return the response
            return new ServiceResponse<UserResponseDto>(Status.OK, userResponseDto, "User retrieved successfully", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(LOGLINE, Constants.METHOD_GET, "Error retrieving user data: {}", e.getMessage());
            return new ServiceResponse<UserResponseDto>(Status.KO, null, "Error retrieving user data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ServiceResponse<List<UserResponseDto>> getUsers() {
        LOGGER.info(LOGLINE, Constants.METHOD_LIST, Constants.IN);
        try {
            List<User> userEntities = userRepository.findAll();
            List<UserResponseDto> userResponseDtos = UserMapper.toUserResponseDtoList(userEntities);
            
             LOGGER.info(LOGLINE, Constants.METHOD_LIST, Constants.OUT);
            // Return the response
            return new ServiceResponse<List<UserResponseDto>>(Status.OK, userResponseDtos, "Users retrieved successfully", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(LOGLINE, Constants.METHOD_LIST, "Error retrieving users data: {}", e.getMessage());
            return new ServiceResponse<List<UserResponseDto>>(Status.KO, null, "Error retrieving users data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ServiceResponse<UserResponseDto> updateUser(String id, UpdateUserDto user) {
        LOGGER.info(LOGLINE, Constants.METHOD_UPDATE, Constants.IN);
        try {
            // Validate the input
            if (id == null || user == null) {
                return new ServiceResponse<UserResponseDto>(Status.KO, null, "User ID and data are required", HttpStatus.BAD_REQUEST);
            }

            User userEntity = userRepository.findById(id).orElse(null);
            if (userEntity == null) {
                return new ServiceResponse<UserResponseDto>(Status.KO, null, "User not found", HttpStatus.NOT_FOUND);
            }

            // Check if the email is already taken by another user
            if (user.getEmail() != null) {
                User existingUser = userRepository.findByEmail(user.getEmail()).orElse(null);
                if (existingUser != null && !existingUser.getId().equals(id)) {
                    return new ServiceResponse<UserResponseDto>(Status.KO, null, "Email already in use by another user", HttpStatus.BAD_REQUEST);
                }
            }
           

            // Update the User entity with new data
            userEntity.setName(user.getName());
            userEntity.setEmail(user.getEmail());
            userEntity.setUpdatedAt(new Date());
            userRepository.save(userEntity);

            // Convert the updated User entity to UserResponseDto
            UserResponseDto userResponseDto = UserMapper.toUserResponseDto(userEntity);
            
             LOGGER.info(LOGLINE, Constants.METHOD_UPDATE, Constants.OUT);
            // Return the response
            return new ServiceResponse<UserResponseDto>(Status.OK, userResponseDto, "User updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(LOGLINE, Constants.METHOD_UPDATE, "Error updating user data: {}", e.getMessage());
            return new ServiceResponse<UserResponseDto>(Status.KO, null, "Error updating user data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ServiceResponse<Boolean> deleteUser(String id) {
        LOGGER.info(LOGLINE, Constants.METHOD_DELETE, Constants.IN);
        try {
            // Validate the input
            if (id == null) {
                return new ServiceResponse<Boolean>(Status.KO, null, "User ID is required", HttpStatus.BAD_REQUEST);
            }

            User userEntity = userRepository.findById(id).orElse(null);
            if (userEntity == null) {
                return new ServiceResponse<Boolean>(Status.KO, null, "User not found", HttpStatus.NOT_FOUND);
            }

            userRepository.delete(userEntity);
            
             LOGGER.info(LOGLINE, Constants.METHOD_DELETE, Constants.OUT);
            // Return the response
            return new ServiceResponse<Boolean>(Status.OK, true, "User deleted successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.error(LOGLINE, Constants.METHOD_DELETE, "Error deleting user data: {}", e.getMessage());
            return new ServiceResponse<Boolean>(Status.KO, null, "Error deleting user data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   
    
}
