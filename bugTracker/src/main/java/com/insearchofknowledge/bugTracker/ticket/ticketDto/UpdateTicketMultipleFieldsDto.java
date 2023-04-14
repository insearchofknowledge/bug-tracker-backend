package com.insearchofknowledge.bugTracker.ticket.ticketDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateTicketMultipleFieldsDto {

    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String title;
    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String description;
    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String typeOfTicket;
    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String ticketPriority;
    @NotNull(message = "Can't be null.")
    @NotBlank(message = "Can't be blank.")
    private String ticketStatus;
    @NotNull(message = "Can't be null.")
    private List<String> devsAssigned;
}
