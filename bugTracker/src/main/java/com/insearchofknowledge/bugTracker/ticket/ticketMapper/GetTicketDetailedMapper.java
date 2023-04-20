package com.insearchofknowledge.bugTracker.ticket.ticketMapper;

import com.insearchofknowledge.bugTracker.comment.commentMapper.GetCommentMapper;
import com.insearchofknowledge.bugTracker.developer.developerMapper.GetDeveloperSimplifiedMapper;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.GetTicketDetailedDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class GetTicketDetailedMapper implements Mapper<Ticket, GetTicketDetailedDto> {

    private final GetDeveloperSimplifiedMapper getDeveloperSimplifiedMapper;
    private final GetCommentMapper getCommentMapper;

    public GetTicketDetailedMapper(GetDeveloperSimplifiedMapper getDeveloperSimplifiedMapper,
                                   @Lazy GetCommentMapper getCommentMapper) {
        this.getDeveloperSimplifiedMapper = getDeveloperSimplifiedMapper;
        this.getCommentMapper = getCommentMapper;
    }

    @Override
    public GetTicketDetailedDto map(Ticket entity) {
        GetTicketDetailedDto getTicketDetailedDto = new GetTicketDetailedDto();
        getTicketDetailedDto.setId(entity.getId());
        getTicketDetailedDto.setTitle(entity.getTitle());
        getTicketDetailedDto.setDescription(entity.getDescription());
        getTicketDetailedDto.setDateCreated(entity.getDateCreated());
        getTicketDetailedDto.setLastDateModified(entity.getLastDateModified());
        getTicketDetailedDto.setTypeOfTicket(entity.getTypeOfTicket());
        getTicketDetailedDto.setTicketPriority(entity.getTicketPriority());
        getTicketDetailedDto.setTicketStatus(entity.getTicketStatus());
        getTicketDetailedDto.setAuthor(getDeveloperSimplifiedMapper.map(entity.getAuthor()));
        getTicketDetailedDto.setDevsAssigned(entity.getDevsAssigned().stream().map(getDeveloperSimplifiedMapper::map).toList());
        // to avoid NullPointerException if a ticket has no comments
        if (entity.getComments() != null) {
            getTicketDetailedDto.setComments(entity.getComments().stream().map(getCommentMapper::map).toList());
        }
        return getTicketDetailedDto;
    }
}
