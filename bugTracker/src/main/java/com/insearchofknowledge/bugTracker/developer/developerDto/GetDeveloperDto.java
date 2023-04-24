package com.insearchofknowledge.bugTracker.developer.developerDto;

import com.insearchofknowledge.bugTracker.comment.commentDto.GetCommentDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectSimplifiedDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.GetTicketSimplifiedDto;
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
    private List<GetTicketSimplifiedDto> ticketsCreated;
    private List<GetProjectSimplifiedDto> projects;
    private List<GetCommentDto> comments;
}
