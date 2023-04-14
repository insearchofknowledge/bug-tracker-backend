package com.insearchofknowledge.bugTracker.ticket.ticketDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateTicketSingleFieldDto {

    private String fieldName;
    @NotNull(message = "Can't be null.")
    private Object fieldValue;
}
