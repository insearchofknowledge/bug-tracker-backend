package com.insearchofknowledge.bugTracker.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final AddTicketMapper addTicketMapper;
    private final GetTicketMapper getTicketMapper;
    private final TicketRepository ticketRepository;

    public GetTicketDto createTicket(AddTicketDto addTicketDto){
        Ticket newTicket = addTicketMapper.map(addTicketDto);
        newTicket.setDateCreated(LocalDateTime.now()/*.truncatedTo(ChronoUnit.SECONDS)*/);
        ticketRepository.save(newTicket);
        return getTicketMapper.map(newTicket);
    }

    public List<GetTicketDto> findAllTickets() {
       return ticketRepository.findAll().stream().map(getTicketMapper::map).toList();
    }
}
