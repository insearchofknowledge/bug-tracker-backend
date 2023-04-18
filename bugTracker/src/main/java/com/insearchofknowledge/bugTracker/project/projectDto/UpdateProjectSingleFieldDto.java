package com.insearchofknowledge.bugTracker.project.projectDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProjectSingleFieldDto {

    private String fieldName;
    private Object fieldValue;
}
