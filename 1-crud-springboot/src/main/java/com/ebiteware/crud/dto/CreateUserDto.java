package com.ebiteware.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getters and setters for all fields
@JsonInclude(JsonInclude.Include.NON_NULL) // include only non-null fields in JSON serialization
@AllArgsConstructor // constructor with all fields
@NoArgsConstructor // no-args constructor
public class CreateUserDto {

    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    private String name;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotNull(message = "Role cannot be null")
    @NotBlank(message = "Role cannot be blank")
    private String role;

    @NotNull(message = "Status cannot be null")
    @NotBlank(message = "Status cannot be blank")
    private String status;
    
}
