package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.comment.CommentDto;
import com.insearchofknowledge.bugTracker.project.ProjectDto;
import com.insearchofknowledge.bugTracker.ticket.TicketDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DeveloperDto {

    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String role;
    private List<TicketDto> ticketsCreated;
    private List<String> ticketsCreatedId;
    private List<ProjectDto> projects;
    private List<String> projectsId;
    private List<CommentDto> comments;
    private List<String> commentsId;
}
