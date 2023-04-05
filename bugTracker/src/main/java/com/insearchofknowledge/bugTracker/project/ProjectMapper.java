package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.comment.CommentMapper;
import com.insearchofknowledge.bugTracker.developer.DeveloperMapper;
import com.insearchofknowledge.bugTracker.developer.DeveloperRepository;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.ticket.TicketMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper implements Mapper<Project, ProjectDto> {

    private final TicketMapper ticketMapper;
    private final DeveloperMapper developerMapper;
    private final CommentMapper commentMapper;
    private final DeveloperRepository developerRepository;

    public ProjectMapper(TicketMapper ticketMapper, @Lazy DeveloperMapper developerMapper, @Lazy CommentMapper commentMapper, DeveloperRepository developerRepository) {
        this.ticketMapper = ticketMapper;
        this.developerMapper = developerMapper;
        this.commentMapper = commentMapper;
        this.developerRepository = developerRepository;
    }

    @Override
    public ProjectDto convertToDto(Project entity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName(entity.getName());
        projectDto.setDescription(entity.getDescription());
        projectDto.setStartDate(entity.getStartDate());
        projectDto.setDeadline(entity.getDeadline());
        projectDto.setTickets(entity.getTickets().stream().map(ticketMapper::convertToDto).toList()); // collect(Collectors.toList())
        projectDto.setAssignedTeam(entity.getAssignedTeam().stream().map(developerMapper::convertToDto).toList());
        projectDto.setComments(entity.getComments().stream().map(commentMapper::convertToDto).toList());
        return projectDto;
    }

    @Override
    public Project convertToEntity(ProjectDto dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setAssignedTeam(developerRepository.findAllById(dto.getDeveloperIds()));
        return project;
    }
}
