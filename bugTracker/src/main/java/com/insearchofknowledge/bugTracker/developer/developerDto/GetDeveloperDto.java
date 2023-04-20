package com.insearchofknowledge.bugTracker.developer.developerDto;

import com.insearchofknowledge.bugTracker.comment.commentDto.GetCommentDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDetailedDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.GetTicketDetailedDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class GetDeveloperDto {

    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String role;
    private List<GetTicketDetailedDto> ticketsCreated;
    private List<GetProjectDetailedDto> projects;
    private List<GetCommentDto> comments;
}
