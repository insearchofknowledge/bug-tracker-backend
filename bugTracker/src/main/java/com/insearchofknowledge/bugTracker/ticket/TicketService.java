package com.insearchofknowledge.bugTracker.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket createIssue(Ticket issue){
        return ticketRepository.save(issue);
    }
}
