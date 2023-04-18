package com.insearchofknowledge.bugTracker.developer.developerDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDeveloperSingleFieldDto {

    private String fieldName;
    @NotNull(message = "The updated value can't be null")
    @NotBlank(message = "The updated value can't be blank")
    private String fieldValue;
}
