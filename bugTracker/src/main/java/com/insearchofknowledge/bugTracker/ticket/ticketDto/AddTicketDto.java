package com.insearchofknowledge.bugTracker.ticket.ticketDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class AddTicketDto {

    @NotNull(message = "Message from DTO TITLE NEEDED!")
    @NotBlank(message = "Message from DTO not blank!!!!!!!!!")
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime lastDateModified;
    private String typeOfTicket;  // will use string instead of int since is less prone to errors in case the order of Enums gets switched up
    private String ticketPriority;  // will use string instead of int since is less prone to errors in case the order of Enums gets switched up
    private String ticketStatus;  // will use string instead of int since is less prone to errors in case the order of Enums gets switched up
    private String author;
    private List<String> devsAssigned;
    private String project;
}
