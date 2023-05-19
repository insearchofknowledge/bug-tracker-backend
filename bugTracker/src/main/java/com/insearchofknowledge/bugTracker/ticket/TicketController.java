package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.ticket.ticketDto.*;
import jakarta.validation.Valid;
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
    public ResponseEntity<GetTicketDetailedDto> addTicket(@RequestBody @Valid AddTicketDto addTicketDto) {
        GetTicketDetailedDto getTicketDetailedDto = ticketService.createNewTicket(addTicketDto);
        return new ResponseEntity<>(getTicketDetailedDto, HttpStatus.CREATED);
    }

    @GetMapping("{ticketId}")
    public ResponseEntity<GetTicketDetailedDto> getTicketById(@PathVariable("ticketId") String ticketId) {
        GetTicketDetailedDto ticket = ticketService.fetchTicketById(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/allTickets")
    public ResponseEntity<List<GetTicketDetailedDto>> getAllTickets() {
        List<GetTicketDetailedDto> tickets = ticketService.fetchAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/author/{developerId}")
    public ResponseEntity<List<GetTicketSimplifiedDto>> getAllByAuthor(@PathVariable("developerId") String developerId) {
        List<GetTicketSimplifiedDto> tickets = ticketService.fetchTicketsByAuthor(developerId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/assignedTo/{developerId}")
    public ResponseEntity<List<GetTicketSimplifiedDto>> getAllTicketsAssignedToDev(@PathVariable("developerId") String developerId) {
        List<GetTicketSimplifiedDto> tickets = ticketService.fetchTicketsAssignedToDev(developerId);
        return ResponseEntity.ok(tickets);
    }

    @PatchMapping("/update/{ticketId}")
    public ResponseEntity<GetTicketDetailedDto> editASingleFieldOfATicket(@PathVariable("ticketId") String id, @RequestBody @Valid UpdateTicketSingleFieldDto updateTicketSingleFieldDto) throws NoSuchFieldException {
        GetTicketDetailedDto updatedTicketDto = ticketService.updateSingleFieldOfATicket(id, updateTicketSingleFieldDto);
        return ResponseEntity.ok(updatedTicketDto);
    }

    @PatchMapping("/updateFields/{ticketId}")
    public ResponseEntity<GetTicketDetailedDto> editMultipleFieldsOfATicket(@PathVariable("ticketId") String id, @RequestBody @Valid UpdateTicketMultipleFieldsDto updateTicketMultipleFieldsDto) {
        GetTicketDetailedDto updatedTicketDto = ticketService.updateMultipleFieldsOfATicket(id, updateTicketMultipleFieldsDto);
        return ResponseEntity.ok(updatedTicketDto);
    }

    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable("ticketId") String id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
