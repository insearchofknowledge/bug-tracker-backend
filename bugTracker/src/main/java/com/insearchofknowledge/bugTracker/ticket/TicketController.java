package com.insearchofknowledge.bugTracker.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/issues")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<Ticket> addIssue(@RequestBody Ticket issue){
        Ticket newIssue = ticketService.createIssue(issue);
        return new ResponseEntity<>(newIssue, HttpStatus.CREATED);
    }

}
