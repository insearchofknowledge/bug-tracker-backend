package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.developer.DeveloperService;
import com.insearchofknowledge.bugTracker.project.projectDto.AddProjectDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDto;
import com.insearchofknowledge.bugTracker.project.projectDto.UpdateProjectSingleFieldDto;
import com.insearchofknowledge.bugTracker.project.projectDto.UpdateProjectTeamDto;
import com.insearchofknowledge.bugTracker.project.projectMapper.AddProjectMapper;
import com.insearchofknowledge.bugTracker.project.projectMapper.GetProjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
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
    private final GetProjectMapper getProjectMapper;
    private final DeveloperService developerService;

    public GetProjectDto createProject(AddProjectDto addProjectDto) {
        Project newProject = addProjectMapper.map(addProjectDto);
        if (newProject.getStartDate() == null) {
            newProject.setStartDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
        return getProjectMapper.map(projectRepository.save(newProject));
    }

    public GetProjectDto fetchProjectById(String projectId) {
        Project fetchedProject = fetchProjectIfItExists(projectId);
        return getProjectMapper.map(fetchedProject);
    }

    public List<GetProjectDto> fetchAllProjects() {
        return projectRepository.findAll().stream().map(getProjectMapper::map).toList();
    }

    public GetProjectDto updateSingleFieldOfAProject(String projectId, UpdateProjectSingleFieldDto updateProjectSingleFieldDto) throws NoSuchFieldException {
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
        return getProjectMapper.map(projectRepository.save(projectToBeUpdated));
    }

    public GetProjectDto updateTeamAssignedToProject(String projectId, UpdateProjectTeamDto updateProjectTeamDto) {
        Project projectToBeUpdated = fetchProjectIfItExists(projectId);
        projectToBeUpdated.setAssignedTeam(developerService.fetchAllDevelopersByIdList(updateProjectTeamDto.getAssignedTeam()));
        return getProjectMapper.map(projectRepository.save(projectToBeUpdated));

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
