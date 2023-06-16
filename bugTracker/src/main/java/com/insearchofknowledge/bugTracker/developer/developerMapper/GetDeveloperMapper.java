package com.insearchofknowledge.bugTracker.developer.developerMapper;

import com.insearchofknowledge.bugTracker.comment.commentMapper.GetCommentMapper;
import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.developer.developerDto.GetDeveloperDto;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.projectMapper.GetProjectSimplifiedMapper;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.GetTicketSimplifiedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDeveloperMapper implements Mapper<Developer, GetDeveloperDto> {

    private final GetTicketSimplifiedMapper getTicketSimplifiedMapper;
    private final GetProjectSimplifiedMapper getProjectSimplifiedMapper;
    private final GetCommentMapper getCommentMapper;

    @Override
    public GetDeveloperDto map(Developer entity) {
        GetDeveloperDto getDeveloperDto = new GetDeveloperDto();
        getDeveloperDto.setId(entity.getId());
        getDeveloperDto.setFirstName(entity.getFirstName());
        getDeveloperDto.setLastName(entity.getLastName());
        getDeveloperDto.setPhone(entity.getPhone());
        getDeveloperDto.setEmail(entity.getEmail());
//        getDeveloperDto.setRole(entity.getRole());
        if (entity.getTicketsCreated() != null) {
            getDeveloperDto.setTicketsCreated(entity.getTicketsCreated().stream().map(getTicketSimplifiedMapper::map).toList());
        }
        if (entity.getProjects() != null) {
            getDeveloperDto.setProjects(entity.getProjects().stream().map(getProjectSimplifiedMapper::map).toList());
        }
        if (entity.getComments() != null) {
            getDeveloperDto.setComments(entity.getComments().stream().map(getCommentMapper::map).toList());
        }
        return getDeveloperDto;
    }
}
