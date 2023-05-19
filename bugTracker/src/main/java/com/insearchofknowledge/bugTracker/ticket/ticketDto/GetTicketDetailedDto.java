package com.insearchofknowledge.bugTracker.ticket.ticketDto;

import com.insearchofknowledge.bugTracker.comment.commentDto.GetCommentDto;
import com.insearchofknowledge.bugTracker.developer.developerDto.GetDeveloperSimplifiedDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDetailedDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectSimplifiedDto;
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
public class GetTicketDetailedDto {

    private String id;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime lastDateModified;
    private TypeOfTicket typeOfTicket;
    private TicketPriority ticketPriority;
    private TicketStatus ticketStatus;
    private GetDeveloperSimplifiedDto author;  // SimplifiedDto reduce excessive data traffic and to avoid StackOverFlow
    private List<GetDeveloperSimplifiedDto> devsAssigned;  // SimplifiedDto reduce excessive data traffic and to avoid StackOverFlow
    private List<GetCommentDto> comments;
    private String project;
}
