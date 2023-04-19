package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.ticket.ticketDto.AddTicketDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.GetTicketDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.UpdateTicketMultipleFieldsDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.UpdateTicketSingleFieldDto;
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
    public ResponseEntity<GetTicketDto> addTicket(@RequestBody @Valid AddTicketDto addTicketDto) {
        GetTicketDto getTicketDto = ticketService.createNewTicket(addTicketDto);
        return new ResponseEntity<>(getTicketDto, HttpStatus.CREATED);
    }

    @GetMapping("{ticketId}")
    public ResponseEntity<GetTicketDto> getTicketById(@PathVariable("ticketId") String ticketId) {
        GetTicketDto ticket = ticketService.fetchTicketById(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/allTickets")
    public ResponseEntity<List<GetTicketDto>> getAllTickets() {
        List<GetTicketDto> tickets = ticketService.fetchAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @PatchMapping("/update/{ticketId}")
    public ResponseEntity<GetTicketDto> editASingleFieldOfATicket(@PathVariable("ticketId") String id, @RequestBody @Valid UpdateTicketSingleFieldDto updateTicketSingleFieldDto) throws NoSuchFieldException {
        GetTicketDto updatedTicketDto = ticketService.updateSingleFieldOfATicket(id, updateTicketSingleFieldDto);
        return ResponseEntity.ok(updatedTicketDto);
    }

    @PatchMapping("/updateFields/{ticketId}")
    public ResponseEntity<GetTicketDto> editMultipleFieldsOfATicket(@PathVariable("ticketId") String id, @RequestBody @Valid UpdateTicketMultipleFieldsDto updateTicketMultipleFieldsDto) {
        GetTicketDto updatedTicketDto = ticketService.updateMultipleFieldsOfATicket(id, updateTicketMultipleFieldsDto);
        return ResponseEntity.ok(updatedTicketDto);
    }

    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable("ticketId") String id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
