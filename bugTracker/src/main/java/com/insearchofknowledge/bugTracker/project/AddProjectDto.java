package com.insearchofknowledge.bugTracker.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddProjectDto {

    private String name;
    private String description;
    private Date deadline;
    private List<String> assignedTeam;

    // Start date will be set in the service layer
}
