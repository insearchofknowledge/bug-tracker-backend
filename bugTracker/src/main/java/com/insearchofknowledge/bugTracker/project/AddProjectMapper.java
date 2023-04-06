package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.developer.DeveloperRepository;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProjectMapper implements Mapper<AddProjectDto, Project> {

    private final DeveloperRepository developerRepository;
    @Override
    public Project map(AddProjectDto addprojectDto) {
        Project project = new Project();
        project.setName(addprojectDto.getName());
        project.setDescription(addprojectDto.getDescription());
        project.setDeadline(addprojectDto.getDeadline());
        project.setAssignedTeam(developerRepository.findAllById(addprojectDto.getAssignedTeam()));

        return project;
    }
}