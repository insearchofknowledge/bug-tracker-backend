package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.developer.DeveloperMapper;
import com.insearchofknowledge.bugTracker.developer.DeveloperRepository;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.ProjectMapper;
import com.insearchofknowledge.bugTracker.project.ProjectRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service

public class TicketMapper implements Mapper<Ticket, TicketDto> {

    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;
    private final DeveloperMapper developerMapper;
    private final ProjectMapper projectMapper;

    public TicketMapper(DeveloperRepository developerRepository, ProjectRepository projectRepository, @Lazy DeveloperMapper developerMapper, @Lazy ProjectMapper projectMapper) {
        this.developerRepository = developerRepository;
        this.projectRepository = projectRepository;
        this.developerMapper = developerMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public TicketDto convertToDto(Ticket entity) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(entity.getId());
        ticketDto.setTitle(entity.getTitle());
        ticketDto.setDescription(entity.getDescription());
        ticketDto.setDateCreated(entity.getDateCreated());
        ticketDto.setLastDateModified(entity.getLastDateModified());
        ticketDto.setTypeOfTicket(entity.getTypeOfTicket());
        ticketDto.setTicketPriority(entity.getTicketPriority());
        ticketDto.setAuthor(developerMapper.convertToDto(entity.getAuthor()));
        ticketDto.setDevsAssigned(entity.getDevsAssigned().stream().map(developerMapper::convertToDto).collect(Collectors.toList()));
        ticketDto.setProject(projectMapper.convertToDto(entity.getProject()));
        return ticketDto;
    }

    @Override
    public Ticket convertToEntity(TicketDto dto) {
        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setTypeOfTicket(TypeOfTicket.values()[dto.getTypeOfTicketNumericValue()]);
        ticket.setTicketPriority(TicketPriority.values()[dto.getTicketPriorityNumericValue()]);
        ticket.setTicketStatus(TicketStatus.values()[dto.getTypeOfTicketNumericValue()]);
        ticket.setAuthor(developerRepository.getReferenceById(dto.getAuthorId()));
        ticket.setDevsAssigned(developerRepository.findAllById(dto.getDevsAssignedId()));
        ticket.setProject(projectRepository.getReferenceById(dto.getProjectId()));
        return ticket;
    }
}
