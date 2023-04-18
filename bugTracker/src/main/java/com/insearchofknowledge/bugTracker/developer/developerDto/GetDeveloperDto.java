package com.insearchofknowledge.bugTracker.developer.developerDto;

import com.insearchofknowledge.bugTracker.comment.GetCommentDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.GetTicketDto;
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
    private List<GetTicketDto> ticketsCreated;
    private List<GetProjectDto> projects;
    private List<GetCommentDto> comments;
}
