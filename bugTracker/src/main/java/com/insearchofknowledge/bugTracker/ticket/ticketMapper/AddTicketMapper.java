package com.insearchofknowledge.bugTracker.ticket.ticketMapper;

import com.insearchofknowledge.bugTracker.developer.DeveloperRepository;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.ProjectRepository;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketPriority;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketStatus;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TypeOfTicket;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.AddTicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddTicketMapper implements Mapper<AddTicketDto, Ticket> {

    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Ticket map(AddTicketDto addTicketDto) {
        Ticket ticket = new Ticket();
        ticket.setTitle(addTicketDto.getTitle());
        ticket.setDescription(addTicketDto.getDescription());
        ticket.setTypeOfTicket(Enum.valueOf(TypeOfTicket.class, addTicketDto.getTypeOfTicket()));
        ticket.setTicketPriority(Enum.valueOf(TicketPriority.class, addTicketDto.getTicketPriority()));
        ticket.setTicketStatus(Enum.valueOf(TicketStatus.class, addTicketDto.getTicketStatus()));
        ticket.setAuthor(developerRepository.getReferenceById(addTicketDto.getAuthor()));
        ticket.setDevsAssigned(developerRepository.findAllById(addTicketDto.getDevsAssigned()));
        ticket.setProject(projectRepository.getReferenceById(addTicketDto.getProject()));
        return ticket;
    }
}
