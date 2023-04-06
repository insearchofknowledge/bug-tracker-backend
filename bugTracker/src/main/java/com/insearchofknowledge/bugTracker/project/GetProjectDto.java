package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.comment.GetCommentDto;
import com.insearchofknowledge.bugTracker.developer.GetDeveloperDto;
import com.insearchofknowledge.bugTracker.ticket.GetTicketDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetProjectDto {

    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date deadline;
    private List<GetTicketDto> tickets;
    private List<GetDeveloperDto> assignedTeam;
    private List<GetCommentDto> comments;

}
