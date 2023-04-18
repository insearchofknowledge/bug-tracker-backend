package com.insearchofknowledge.bugTracker.ticket.ticketDto;

import com.insearchofknowledge.bugTracker.comment.GetCommentDto;
import com.insearchofknowledge.bugTracker.developer.developerDto.GetDeveloperSimplifiedDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDto;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketPriority;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketStatus;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TypeOfTicket;
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
    private GetDeveloperSimplifiedDto author;
    private List<GetDeveloperSimplifiedDto> devsAssigned;
    private List<GetCommentDto> comments;
    private GetProjectDto project;
}
