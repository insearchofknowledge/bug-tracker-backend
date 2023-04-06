package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.developer.GetDeveloperDto;
import com.insearchofknowledge.bugTracker.project.GetProjectDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GetTicketDto {

    private String id;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime lastDateModified;
    private TypeOfTicket typeOfTicket;
    private TicketPriority ticketPriority;
    private TicketStatus ticketStatus;
    private GetDeveloperDto author;
    private List<GetDeveloperDto> devsAssigned;
    private GetProjectDto project;
}
