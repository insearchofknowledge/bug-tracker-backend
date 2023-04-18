package com.insearchofknowledge.bugTracker.project.projectMapper;

import com.insearchofknowledge.bugTracker.developer.DeveloperRepository;
import com.insearchofknowledge.bugTracker.developer.DeveloperService;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.projectDto.AddProjectDto;
import com.insearchofknowledge.bugTracker.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProjectMapper implements Mapper<AddProjectDto, Project> {

    private final DeveloperService developerService;

    @Override
    public Project map(AddProjectDto addprojectDto) {
        Project project = new Project();
        project.setName(addprojectDto.getName());
        project.setDescription(addprojectDto.getDescription());
        if(addprojectDto.getStartDate() != null) {
            project.setStartDate(addprojectDto.getStartDate());
        }
        project.setDeadline(addprojectDto.getDeadline());
        project.setAssignedTeam(developerService.fetchAllDevelopersByIdList(addprojectDto.getAssignedTeam()));
        return project;
    }
}
