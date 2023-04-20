package com.insearchofknowledge.bugTracker.ticket.ticketDto;

import com.insearchofknowledge.bugTracker.developer.developerDto.GetDeveloperSimplifiedDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetTicketSimplifiedDto {

    private String id;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private GetDeveloperSimplifiedDto author;
}
