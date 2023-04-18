package com.insearchofknowledge.bugTracker.developer.developerDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddDeveloperDto {

    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String firstName;
    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String lastName;
    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String phone;
    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String email;
    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String password;
}
