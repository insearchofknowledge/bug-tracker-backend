package com.insearchofknowledge.bugTracker.project.projectDto;

import com.insearchofknowledge.bugTracker.developer.developerDto.GetDeveloperSimplifiedDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.GetTicketDetailedDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetProjectDetailedDto {

    private String id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
    private List<GetTicketDetailedDto> tickets;
    private List<GetDeveloperSimplifiedDto> assignedTeam;  // SimplifiedDto reduce excessive data traffic and to avoid StackOverFlow
}