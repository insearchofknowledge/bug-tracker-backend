package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.developer.DeveloperService;
import com.insearchofknowledge.bugTracker.project.projectDto.*;
import com.insearchofknowledge.bugTracker.project.projectMapper.AddProjectMapper;
import com.insearchofknowledge.bugTracker.project.projectMapper.GetProjectDetailedMapper;
import com.insearchofknowledge.bugTracker.project.projectMapper.GetProjectSimplifiedMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final AddProjectMapper addProjectMapper;
    private final GetProjectDetailedMapper getProjectDetailedMapper;
    private final GetProjectSimplifiedMapper getProjectSimplifiedMapper;
    private final DeveloperService developerService;

    public GetProjectDetailedDto createNewProject(AddProjectDto addProjectDto) {
        Project newProject = addProjectMapper.map(addProjectDto);
        if (newProject.getStartDate() == null) {
            newProject.setStartDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
        return getProjectDetailedMapper.map(projectRepository.save(newProject));
    }

    public GetProjectDetailedDto fetchProjectById(String projectId) {
        Project fetchedProject = fetchProjectIfItExists(projectId);
        return getProjectDetailedMapper.map(fetchedProject);
    }

    public List<GetProjectSimplifiedDto> fetchAllProjects() {
        return projectRepository.findAll().stream().map(getProjectSimplifiedMapper::map).toList();
    }

    public List<GetProjectSimplifiedDto> fetchAllProjectsThisDeveloperIsPartOf(String developerId) {
        developerService.checkIfDeveloperIdExists(developerId);
        return projectRepository.findByAssignedTeamId(developerId).stream().map(getProjectSimplifiedMapper::map).toList();
    }

    public GetProjectDetailedDto updateSingleFieldOfAProject(String projectId, UpdateProjectSingleFieldDto updateProjectSingleFieldDto) throws NoSuchFieldException {
        Project projectToBeUpdated = fetchProjectIfItExists(projectId);

        switch (updateProjectSingleFieldDto.getFieldName()) {
            case "name":
                projectToBeUpdated.setName((String) updateProjectSingleFieldDto.getFieldValue());
                break;
            case "description":
                projectToBeUpdated.setDescription((String) updateProjectSingleFieldDto.getFieldValue());
                break;
            case "startDate":
                projectToBeUpdated.setStartDate((LocalDateTime) updateProjectSingleFieldDto.getFieldValue());
                break;
            case "deadline":
                projectToBeUpdated.setDeadline((LocalDateTime) updateProjectSingleFieldDto.getFieldValue());
                break;
            default:
                throw new NoSuchFieldException("Invalid field name, or specified field is not to be modified");
        }
        return getProjectDetailedMapper.map(projectRepository.save(projectToBeUpdated));
    }

    public GetProjectDetailedDto updateTeamAssignedToProject(String projectId, UpdateProjectTeamDto updateProjectTeamDto) {
        Project projectToBeUpdated = fetchProjectIfItExists(projectId);
        projectToBeUpdated.setAssignedTeam(developerService.fetchAllDevelopersByIdList(updateProjectTeamDto.getAssignedTeam()));
        return getProjectDetailedMapper.map(projectRepository.save(projectToBeUpdated));

    }

    // Only admins should be able to perform this
    public void deleteProject(String projectId) {
        projectRepository.deleteById(projectId);
    }

    private Project fetchProjectIfItExists(String projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            return projectOptional.get();
        } else {
            throw new EntityNotFoundException("Project with id '" + projectId + "' not found");
        }
    }

}
