package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.ProjectMapper;
import com.insearchofknowledge.bugTracker.ticket.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeveloperMapper implements Mapper<Developer, DeveloperDto> {

    private final TicketMapper ticketMapper;
    private final ProjectMapper projectMapper;

    @Override
    public DeveloperDto convertToDto(Developer entity) {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setFirstName(entity.getFirstName());
        developerDto.setLastName(entity.getLastName());
        developerDto.setPhone(entity.getPhone());
        developerDto.setEmail(entity.getEmail());
        developerDto.setRole(entity.getRole());
        developerDto.setTicketsCreated(entity.getTicketsCreated().stream().map(ticketMapper::convertToDto).toList());
        developerDto.setProjects(entity.getProjects().stream().map(projectMapper::convertToDto).toList());
        return developerDto;
    }

    @Override
    public Developer convertToEntity(DeveloperDto dto) {
        Developer developer = new Developer();
        developer.setFirstName(dto.getFirstName());
        developer.setLastName(dto.getLastName());
        developer.setPhone(dto.getPhone());
        developer.setEmail(dto.getEmail());
        developer.setPassword(dto.getPassword());
        return developer;
    }
}
