package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.GetProjectMapper;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.GetTicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDeveloperMapper implements Mapper<Developer, GetDeveloperDto> {

    private final GetTicketMapper getTicketMapper;
    private final GetProjectMapper getProjectMapper;

    @Override
    public GetDeveloperDto map(Developer entity) {
        GetDeveloperDto getDeveloperDto = new GetDeveloperDto();
        getDeveloperDto.setId(entity.getId());
        getDeveloperDto.setFirstName(entity.getFirstName());
        getDeveloperDto.setLastName(entity.getLastName());
        getDeveloperDto.setPhone(entity.getPhone());
        getDeveloperDto.setEmail(entity.getEmail());
        getDeveloperDto.setRole(entity.getRole());
        getDeveloperDto.setTicketsCreated(entity.getTicketsCreated().stream().map(getTicketMapper::map).toList());
        getDeveloperDto.setProjects(entity.getProjects().stream().map(getProjectMapper::map).toList());
        // COMMENTS ?!?!
        return getDeveloperDto;
    }
}
