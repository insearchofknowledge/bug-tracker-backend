package com.insearchofknowledge.bugTracker.project.projectMapper;

import com.insearchofknowledge.bugTracker.developer.developerMapper.GetDeveloperSimplifiedMapper;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDetailedDto;
import com.insearchofknowledge.bugTracker.project.Project;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.GetTicketDetailedMapper;
import org.springframework.stereotype.Service;

@Service
public class GetProjectDetailedMapper implements Mapper<Project, GetProjectDetailedDto> {

    private final GetTicketDetailedMapper getTicketDetailedMapper;
    private final GetDeveloperSimplifiedMapper getDeveloperSimplifiedMapper;

    public GetProjectDetailedMapper(GetTicketDetailedMapper getTicketDetailedMapper, GetDeveloperSimplifiedMapper getDeveloperSimplifiedMapper) {
        this.getTicketDetailedMapper = getTicketDetailedMapper;
        this.getDeveloperSimplifiedMapper = getDeveloperSimplifiedMapper;
    }

    @Override
    public GetProjectDetailedDto map(Project entity) {
        GetProjectDetailedDto getProjectDetailedDto = new GetProjectDetailedDto();
        getProjectDetailedDto.setId(entity.getId());
        getProjectDetailedDto.setName(entity.getName());
        getProjectDetailedDto.setDescription(entity.getDescription());
        getProjectDetailedDto.setStartDate(entity.getStartDate());
        getProjectDetailedDto.setDeadline(entity.getDeadline());
        // to avoid NullPointerException when creating a new project that has no tickets
        if (entity.getTickets() != null) {
            getProjectDetailedDto.setTickets(entity.getTickets().stream().map(getTicketDetailedMapper::map).toList()); // collect(Collectors.toList())
        }
        getProjectDetailedDto.setAssignedTeam(entity.getAssignedTeam().stream().map(getDeveloperSimplifiedMapper::map).toList());
        return getProjectDetailedDto;
    }
}
