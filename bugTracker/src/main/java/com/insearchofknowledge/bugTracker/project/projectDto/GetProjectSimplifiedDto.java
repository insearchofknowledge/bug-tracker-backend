package com.insearchofknowledge.bugTracker.project.projectDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GetProjectSimplifiedDto {

    private String id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
}
