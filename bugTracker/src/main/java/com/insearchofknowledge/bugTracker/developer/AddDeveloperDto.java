package com.insearchofknowledge.bugTracker.developer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddDeveloperDto {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
}
