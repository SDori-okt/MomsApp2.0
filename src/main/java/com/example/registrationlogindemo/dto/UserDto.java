package com.example.registrationlogindemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String firstName;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String lastName;
    @NotEmpty(message = "A mező kitöltése kötelező")
    @Email
    private String email;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String username;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String password;

    private Long yearOfBirth;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String location;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String street;

    private Long house_number;

}
