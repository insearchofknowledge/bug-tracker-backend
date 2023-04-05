package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.developer.DeveloperDto;
import com.insearchofknowledge.bugTracker.project.ProjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TicketDto {

    private String id;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime lastDateModified;
    private TypeOfTicket typeOfTicket;
    private int typeOfTicketNumericValue;
    private TicketPriority ticketPriority;
    private int ticketPriorityNumericValue;
    private TicketStatus ticketStatus;
    private int ticketStatusNumericValue;
    private DeveloperDto author;
    private String authorId;
    private List<DeveloperDto> devsAssigned;
    private List<String> devsAssignedId;
    private ProjectDto project;
    private String projectId;
}
