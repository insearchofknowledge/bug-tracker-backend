package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.developer.DeveloperRepository;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.ProjectRepository;
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
        ticket.setTypeOfTicket(TypeOfTicket.values()[addTicketDto.getTypeOfTicket()]);
        ticket.setTicketPriority(TicketPriority.values()[addTicketDto.getTicketPriority()]);
        ticket.setTicketStatus(TicketStatus.values()[addTicketDto.getTypeOfTicket()]);
        ticket.setAuthor(developerRepository.getReferenceById(addTicketDto.getAuthor()));
        ticket.setDevsAssigned(developerRepository.findAllById(addTicketDto.getDevsAssigned()));
        ticket.setProject(projectRepository.getReferenceById(addTicketDto.getProject()));
        return ticket;
    }
}
