package com.insearchofknowledge.bugTracker.ticket.ticketMapper;

import com.insearchofknowledge.bugTracker.developer.developerMapper.GetDeveloperSimplifiedMapper;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.GetTicketSimplifiedDto;
import org.springframework.stereotype.Service;

@Service
public class GetTicketSimplifiedMapper implements Mapper<Ticket, GetTicketSimplifiedDto> {

    GetDeveloperSimplifiedMapper getDeveloperSimplifiedMapper;

    @Override
    public GetTicketSimplifiedDto map(Ticket ticket) {
        GetTicketSimplifiedDto getTicketSimplifiedDto = new GetTicketSimplifiedDto();
        getTicketSimplifiedDto.setId(ticket.getId());
        getTicketSimplifiedDto.setTitle(ticket.getTitle());
        getTicketSimplifiedDto.setDescription(ticket.getDescription());
        getTicketSimplifiedDto.setDateCreated(ticket.getDateCreated());
        getTicketSimplifiedDto.setAuthor(getDeveloperSimplifiedMapper.map(ticket.getAuthor()));
        return getTicketSimplifiedDto;
    }
}
