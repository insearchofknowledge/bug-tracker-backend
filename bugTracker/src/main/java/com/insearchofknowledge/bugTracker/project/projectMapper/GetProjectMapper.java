package com.insearchofknowledge.bugTracker.project.projectMapper;

import com.insearchofknowledge.bugTracker.developer.developerMapper.GetDeveloperMapper;
import com.insearchofknowledge.bugTracker.developer.developerMapper.GetDeveloperSimplifiedDtoMapper;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDto;
import com.insearchofknowledge.bugTracker.project.Project;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.GetTicketMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class GetProjectMapper implements Mapper<Project, GetProjectDto> {

    private final GetTicketMapper getTicketMapper;
    private final GetDeveloperMapper getDeveloperMapper;
    private final GetDeveloperSimplifiedDtoMapper getDeveloperSimplifiedDtoMapper;

    public GetProjectMapper(GetTicketMapper getTicketMapper, @Lazy GetDeveloperMapper getDeveloperMapper, GetDeveloperSimplifiedDtoMapper getDeveloperSimplifiedDtoMapper) {
        this.getTicketMapper = getTicketMapper;
        this.getDeveloperMapper = getDeveloperMapper;
        this.getDeveloperSimplifiedDtoMapper = getDeveloperSimplifiedDtoMapper;
    }

    @Override
    public GetProjectDto map(Project entity) {
        GetProjectDto getProjectDto = new GetProjectDto();
        getProjectDto.setId(entity.getId());
        getProjectDto.setName(entity.getName());
        getProjectDto.setDescription(entity.getDescription());
        getProjectDto.setStartDate(entity.getStartDate());
        getProjectDto.setDeadline(entity.getDeadline());
        // to avoid NullPointerException when creating a new project that has no tickets
        if (entity.getTickets() != null) {
            getProjectDto.setTickets(entity.getTickets().stream().map(getTicketMapper::map).toList()); // collect(Collectors.toList())
        }
        getProjectDto.setAssignedTeam(entity.getAssignedTeam().stream().map(getDeveloperSimplifiedDtoMapper::map).toList());
        return getProjectDto;
    }
}