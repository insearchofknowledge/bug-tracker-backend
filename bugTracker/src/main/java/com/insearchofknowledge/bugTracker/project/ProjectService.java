package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.project.projectDto.AddProjectDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDto;
import com.insearchofknowledge.bugTracker.project.projectMapper.AddProjectMapper;
import com.insearchofknowledge.bugTracker.project.projectMapper.GetProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final AddProjectMapper addProjectMapper;
    private final GetProjectMapper getProjectMapper;

    public GetProjectDto createProject(AddProjectDto addProjectDto) {
        Project newProject = addProjectMapper.map(addProjectDto);
        newProject.setStartDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return getProjectMapper.map(projectRepository.save(newProject));
    }

//    public GetProjectDto getProjectById(String projectId) {
//
//    }
//
//    public List<GetProjectDto> getAllProjects() {
//
//    }

    // Only admins should be able to perform this
    public void deleteProject (String projectId) {
        projectRepository.deleteById(projectId);
    }

}
