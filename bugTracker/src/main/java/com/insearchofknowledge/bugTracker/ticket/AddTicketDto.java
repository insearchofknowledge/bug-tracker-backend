package com.insearchofknowledge.bugTracker.ticket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class AddTicketDto {

    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime lastDateModified;
    private int typeOfTicket;
    private int ticketPriority;
    private int ticketStatus;
    private String author;
    private List<String> devsAssigned;
    private String project;
}
