package com.insearchofknowledge.bugTracker.ticket.ticketMapper;

import com.insearchofknowledge.bugTracker.comment.GetCommentMapper;
import com.insearchofknowledge.bugTracker.developer.developerMapper.GetDeveloperSimplifiedDtoMapper;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.GetTicketDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class GetTicketMapper implements Mapper<Ticket, GetTicketDto> {

    private final GetDeveloperSimplifiedDtoMapper getDeveloperSimplifiedDtoMapper;
    private final GetCommentMapper getCommentMapper;

    public GetTicketMapper(GetDeveloperSimplifiedDtoMapper getDeveloperSimplifiedDtoMapper,
                           @Lazy GetCommentMapper getCommentMapper) {
        this.getDeveloperSimplifiedDtoMapper = getDeveloperSimplifiedDtoMapper;
        this.getCommentMapper = getCommentMapper;
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
        getTicketDto.setAuthor(getDeveloperSimplifiedDtoMapper.map(entity.getAuthor()));
        getTicketDto.setDevsAssigned(entity.getDevsAssigned().stream().map(getDeveloperSimplifiedDtoMapper::map).toList());
        // to avoid NullPointerException if a ticket has no comments
        if (entity.getComments() != null) {
            getTicketDto.setComments(entity.getComments().stream().map(getCommentMapper::map).toList());
        }
        return getTicketDto;
    }
}
