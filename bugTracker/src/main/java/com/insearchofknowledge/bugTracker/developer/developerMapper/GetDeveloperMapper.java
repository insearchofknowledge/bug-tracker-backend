package com.insearchofknowledge.bugTracker.developer.developerMapper;

import com.insearchofknowledge.bugTracker.comment.GetCommentMapper;
import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.developer.developerDto.GetDeveloperDto;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.projectMapper.GetProjectMapper;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.GetTicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDeveloperMapper implements Mapper<Developer, GetDeveloperDto> {

    private final GetTicketMapper getTicketMapper;
    private final GetProjectMapper getProjectMapper;
    private final GetCommentMapper getCommentMapper;

    @Override
    public GetDeveloperDto map(Developer entity) {
        GetDeveloperDto getDeveloperDto = new GetDeveloperDto();
        getDeveloperDto.setId(entity.getId());
        getDeveloperDto.setFirstName(entity.getFirstName());
        getDeveloperDto.setLastName(entity.getLastName());
        getDeveloperDto.setPhone(entity.getPhone());
        getDeveloperDto.setEmail(entity.getEmail());
        getDeveloperDto.setRole(entity.getRole());
        if (entity.getTicketsCreated() != null) {
            getDeveloperDto.setTicketsCreated(entity.getTicketsCreated().stream().map(getTicketMapper::map).toList());
        }
        if (entity.getProjects() != null) {
            getDeveloperDto.setProjects(entity.getProjects().stream().map(getProjectMapper::map).toList());
        }
        if (entity.getComments() != null) {
            getDeveloperDto.setComments(entity.getComments().stream().map(getCommentMapper::map).toList());
        }
        return getDeveloperDto;
    }
}
