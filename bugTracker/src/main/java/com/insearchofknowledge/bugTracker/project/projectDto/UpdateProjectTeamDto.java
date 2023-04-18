package com.insearchofknowledge.bugTracker.project.projectDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProjectTeamDto {

    private List<String> assignedTeam;
}
