package com.ebiteware.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getters and setters for all fields
@JsonInclude(JsonInclude.Include.NON_NULL) // include only non-null fields in JSON serialization
@AllArgsConstructor // constructor with all fields
@NoArgsConstructor // no-args constructor
public class UpdateUserDto {

    private String name;
    private String email;
    private String password;
    private String role;
    private String status;
    private String updatedAt;

}
