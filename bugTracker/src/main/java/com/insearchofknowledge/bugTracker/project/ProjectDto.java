package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.comment.CommentDto;
import com.insearchofknowledge.bugTracker.developer.DeveloperDto;
import com.insearchofknowledge.bugTracker.ticket.TicketDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto {

    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date deadline;
    private List<TicketDto> tickets;
    private List<String> ticketIds;
    private List<DeveloperDto> assignedTeam;
    private List<String> developerIds;
    private List<CommentDto> comments;
    private List<String> commentIds;
}
