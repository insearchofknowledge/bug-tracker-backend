package com.insearchofknowledge.bugTracker.project.projectMapper;

import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.Project;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectSimplifiedDto;
import org.springframework.stereotype.Service;

@Service
public class GetProjectSimplifiedMapper implements Mapper<Project, GetProjectSimplifiedDto> {

    @Override
    public GetProjectSimplifiedDto map(Project project) {
        GetProjectSimplifiedDto getProjectSimplifiedDto = new GetProjectSimplifiedDto();
        getProjectSimplifiedDto.setId(project.getId());
        getProjectSimplifiedDto.setName(project.getName());
        getProjectSimplifiedDto.setDescription(project.getDescription());
        getProjectSimplifiedDto.setStartDate(project.getStartDate());
        getProjectSimplifiedDto.setDeadline(project.getDeadline());
        return getProjectSimplifiedDto;
    }
}
