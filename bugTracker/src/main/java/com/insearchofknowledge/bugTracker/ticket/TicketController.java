package com.insearchofknowledge.bugTracker.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<GetTicketDto> addTicket(@RequestBody AddTicketDto addTicketDto){
        GetTicketDto getTicketDto = ticketService.createTicket(addTicketDto);
        return new ResponseEntity<>(getTicketDto, HttpStatus.CREATED);
    }

    @GetMapping("/allTickets")
    public ResponseEntity<List<GetTicketDto>> getAllTickets() {
        List<GetTicketDto> tickets = ticketService.findAllTickets();
        return ResponseEntity.ok(tickets);
    }

}
