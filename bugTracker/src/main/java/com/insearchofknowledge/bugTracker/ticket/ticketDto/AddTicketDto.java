package com.insearchofknowledge.bugTracker.ticket.ticketDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotNull(message = "Title required!")
    @NotBlank(message = "Title required")
    private String title;
    @NotNull(message = "Description required")
    @NotBlank(message = "Description required")
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime lastDateModified;
    @NotNull(message = "Type required")
    @NotBlank(message = "Type required")
    private String typeOfTicket;
    // will use string instead of int since is less prone to errors in case the order of Enums gets switched up
    @NotNull(message = "Priority required")
    @NotBlank(message = "Priority required")
    private String ticketPriority;  // will use string instead of int since is less prone to errors in case the order of Enums gets switched up
    @NotNull(message = "Status required")
    @NotBlank(message = "Status required")
    private String ticketStatus;  // will use string instead of int since is less prone to errors in case the order of Enums gets switched up
//    @NotNull(message = "Author required")
//    @NotBlank(message = "Author required")
//    private String author;
    @NotEmpty(message = "The ticket has to be assigned to someone")
    private List<String> devsAssigned;
    @NotNull(message = "The ticket needs to be assigned to a project")
    @NotBlank(message = "The ticket needs to be assigned to a project")
    private String project;
}
