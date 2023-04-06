package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.comment.GetCommentMapper;
import com.insearchofknowledge.bugTracker.developer.GetDeveloperMapper;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.ticket.GetTicketMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class GetProjectMapper implements Mapper<Project, GetProjectDto> {

    private final GetTicketMapper getTicketMapper;
    private final GetDeveloperMapper getDeveloperMapper;
    private final GetCommentMapper getCommentMapper;

    public GetProjectMapper(GetTicketMapper getTicketMapper, @Lazy GetDeveloperMapper getDeveloperMapper, @Lazy GetCommentMapper getCommentMapper) {
        this.getTicketMapper = getTicketMapper;
        this.getDeveloperMapper = getDeveloperMapper;
        this.getCommentMapper = getCommentMapper;
    }

    @Override
    public GetProjectDto map(Project entity) {
        GetProjectDto getProjectDto = new GetProjectDto();
        getProjectDto.setName(entity.getName());
        getProjectDto.setDescription(entity.getDescription());
        getProjectDto.setStartDate(entity.getStartDate());
        getProjectDto.setDeadline(entity.getDeadline());
        getProjectDto.setTickets(entity.getTickets().stream().map(getTicketMapper::map).toList()); // collect(Collectors.toList())
        getProjectDto.setAssignedTeam(entity.getAssignedTeam().stream().map(getDeveloperMapper::map).toList());
        getProjectDto.setComments(entity.getComments().stream().map(getCommentMapper::map).toList());
        return getProjectDto;
    }

}