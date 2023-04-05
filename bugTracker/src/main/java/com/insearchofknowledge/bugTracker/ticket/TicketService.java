package com.insearchofknowledge.bugTracker.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;

    public TicketDto createTicket(TicketDto ticketDto){
        Ticket newTicket = ticketMapper.convertToEntity(ticketDto);
        newTicket.setDateCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        ticketRepository.save(newTicket);
        return ticketMapper.convertToDto(newTicket);
    }
}
