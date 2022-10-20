package com.example.registrationlogindemo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String email;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String username;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String password;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String yearOfBirth;
    @NotNull(message = "A mező kitöltése kötelező")
    private Long postalCode;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String location;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String street;
    @NotNull(message = "A mező kitöltése kötelező")
    private Long house_number;


    @NotEmpty(message = "A mező kitöltése kötelező")
    private String childName;
    @NotEmpty(message = "A mező kitöltése kötelező")
    private String childGender;
    @NotNull(message = "A mező kitöltése kötelező")
    private String childDateOfBirth;


}
