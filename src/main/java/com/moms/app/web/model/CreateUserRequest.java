package com.moms.app.web.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateUserRequest {

    @NotBlank(message = "A mező kitöltése kötelező")
    private String firstName;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String lastName;

    @NotBlank(message = "A mező kitöltése kötelező")
    @Email
    private String email;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String username;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String password;

    @NotNull(message = "A mező kitöltése kötelező")
    private LocalDate dateOfBirth;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String location;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String street;

    private Long houseNumber;
}

