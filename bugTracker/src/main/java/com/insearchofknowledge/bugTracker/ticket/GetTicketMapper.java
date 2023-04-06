package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.developer.GetDeveloperMapper;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service

public class GetTicketMapper implements Mapper<Ticket, GetTicketDto> {

    private final GetDeveloperMapper getDeveloperMapper;

    public GetTicketMapper(@Lazy GetDeveloperMapper getDeveloperMapper) {
        this.getDeveloperMapper = getDeveloperMapper;
    }

    @Override
    public GetTicketDto map(Ticket entity) {
        GetTicketDto getTicketDto = new GetTicketDto();
        getTicketDto.setId(entity.getId());
        getTicketDto.setTitle(entity.getTitle());
        getTicketDto.setDescription(entity.getDescription());
        getTicketDto.setDateCreated(entity.getDateCreated());
        getTicketDto.setLastDateModified(entity.getLastDateModified());
        getTicketDto.setTypeOfTicket(entity.getTypeOfTicket());
        getTicketDto.setTicketPriority(entity.getTicketPriority());
        getTicketDto.setTicketStatus(entity.getTicketStatus());
        getTicketDto.setAuthor(getDeveloperMapper.map(entity.getAuthor()));
        getTicketDto.setDevsAssigned(entity.getDevsAssigned().stream().map(getDeveloperMapper::map).collect(Collectors.toList()));
        return getTicketDto;
    }
}
