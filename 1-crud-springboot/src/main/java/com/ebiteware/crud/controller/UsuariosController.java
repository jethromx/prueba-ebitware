package com.ebiteware.crud.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebiteware.crud.config.Constants;
import com.ebiteware.crud.dto.CreateUserDto;
import com.ebiteware.crud.dto.ServiceResponse;
import com.ebiteware.crud.dto.UpdateUserDto;
import com.ebiteware.crud.dto.UserResponseDto;
import com.ebiteware.crud.enums.Status;
import com.ebiteware.crud.exceptions.CustomException;
import com.ebiteware.crud.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/${api.version}/usuarios")
@Tag(name = "Usuarios", description = "Api de Usuarios")
public class UsuariosController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuariosController.class);
    
    private static final String LOGLINE ="UsuariosController {} - {}";
    
    private final UserService userService;

    public UsuariosController(UserService usuariosService) {
        this.userService = usuariosService;
    }


    @PostMapping( value = "")
    @Operation(summary = "Crear usuario", description = "Crear un nuevo usuario")
    public ResponseEntity<?> createUser(@Valid   @RequestBody CreateUserDto entity,BindingResult bindingResult) {
        LOGGER.info(LOGLINE,Constants.METHOD_CREATE, Constants.IN);

        if (bindingResult.hasErrors()) {   
            LOGGER.error(LOGLINE, Constants.METHOD_CREATE, bindingResult.getAllErrors().get(0).getDefaultMessage());           
            throw  new CustomException(bindingResult.getAllErrors().get(0).getDefaultMessage(), "003");		
        }
        
        ServiceResponse<UserResponseDto> response = userService.createUser(entity);
        
        return handleResponse(response, Constants.METHOD_CREATE);
    }


    @GetMapping(value ="")
    @Operation(summary = "Listar usuarios", description = "Listar todos los usuarios")
    public ResponseEntity<?> getUsers() {
        LOGGER.info(LOGLINE,Constants.METHOD_LIST, Constants.IN);

        ServiceResponse<List<UserResponseDto>> response = userService.getUsers();
        return handleResponse(response, Constants.METHOD_LIST);
    }

    @GetMapping( value = "/{id}")
    @Operation(summary = "Obtener usuario", description = "Obtener un usuario por su ID")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        LOGGER.info(LOGLINE,Constants.METHOD_GET, Constants.IN);

        ServiceResponse<UserResponseDto> response = userService.getUser(id);
        return handleResponse(response, Constants.METHOD_GET);
    }

   
    @PatchMapping(
        value= "/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualizar un usuario por su ID")
    public ResponseEntity<?> updateUser(
        @PathVariable String id,
        @Valid @RequestBody UpdateUserDto user,
        BindingResult bindingResult) {
        LOGGER.info(LOGLINE,Constants.METHOD_UPDATE, Constants.IN);

        ServiceResponse<UserResponseDto> response = userService.updateUser(id,user);
        return handleResponse(response, Constants.METHOD_UPDATE);

    }

    @DeleteMapping(value ="/{id}")
    @Operation(summary = "Eliminar usuario", description = "Eliminar un usuario por su ID")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        LOGGER.info(LOGLINE,Constants.METHOD_DELETE, Constants.IN);
        
        ServiceResponse<Boolean> response = userService.deleteUser(id);
        return handleResponse(response, Constants.METHOD_DELETE);
    }



    // Handle the response from the service
    // and return the appropriate HTTP response
     private ResponseEntity<?> handleResponse(ServiceResponse<?> response,String method) {
        LOGGER.info(LOGLINE, response, Constants.IN);
        //Happy path  - STATUS OK && RESPONSE OBJECT != NULL
        if (response.getStatus().equals(Status.OK) && response.getResponseObject() != null) {  
            LOGGER.info(LOGLINE, "==== 1 ===", Constants.IN);       
            LOGGER.info(LOGLINE, method, Constants.OUT);
            return new ResponseEntity<>(response.getResponseObject(), response.getHttpCode());
         // Error - STATUS KO && RESPONSE OBJECT == NULL   
        } else if (response.getStatus().equals(Status.OK) && response.getResponseObject() == null) {
             LOGGER.info(LOGLINE, "==== 2 ===", Constants.IN);  
               LOGGER.info(LOGLINE, method, Constants.OUT);
               return new ResponseEntity<>(buildErrorJson(response.getMessage()), response.getHttpCode());
    
        // Error - STATUS KO && RESPONSE OBJECT != NULL
        } else if (response.getStatus().equals(Status.KO) && response.getResponseObject() != null) {
             LOGGER.info(LOGLINE, "==== 3 ===", Constants.IN);  
               LOGGER.error(LOGLINE, method, Constants.ERROR);
               return new ResponseEntity<>(response.getResponseObject(), response.getHttpCode());
        }
        // Error - STATUS KO && RESPONSE OBJECT == NULL
        else if (response.getStatus().equals(Status.KO) && response.getResponseObject() == null) {
             LOGGER.info(LOGLINE, "==== 3 ===", Constants.IN);  
               LOGGER.error(LOGLINE, method, Constants.ERROR);
               return new ResponseEntity<>(buildErrorJson(response.getMessage()), response.getHttpCode());     
        // Error - ALL OTHER CASES
        } else {
             LOGGER.info(LOGLINE, "==== 4 ===", Constants.IN);  
               LOGGER.error(LOGLINE, method, Constants.ERROR);
               throw  new CustomException("An unexpected error occurred , please try again later", "100");               
        }
    }

    // Helper para envolver el mensaje en JSON
    private Map<String, String> buildErrorJson(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("message", message);
        return error;
    }

    
}
