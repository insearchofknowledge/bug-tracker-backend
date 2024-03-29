package com.insearchofknowledge.bugTracker.project.projectDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddProjectDto {

    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
    private List<String> assignedTeam;
}
