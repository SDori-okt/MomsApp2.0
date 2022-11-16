package com.example.registrationlogindemo.web.model;

import com.example.registrationlogindemo.persistence.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateEventRequest {

    @NotBlank(message = "A mező kitöltése kötelező")
    private String eventName;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String description;

    @NotBlank(message = "A mező kitöltése kötelező")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private LocalDateTime eventDateTime;

    @JsonProperty(value = "userId")
    private UserEntity userEntity;

}
