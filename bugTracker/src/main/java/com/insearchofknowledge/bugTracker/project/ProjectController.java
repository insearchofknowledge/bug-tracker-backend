package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.developer.Role;
import com.insearchofknowledge.bugTracker.project.projectDto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<GetProjectDetailedDto> addProject(@RequestBody AddProjectDto addProjectDto) {
        return new ResponseEntity<>(projectService.createNewProject(addProjectDto), HttpStatus.CREATED);
    }

    @GetMapping("{projectId}")
    public ResponseEntity<GetProjectDetailedDto> getProjectById(@PathVariable("projectId") String id) {
        GetProjectDetailedDto getProjectDetailedDto = projectService.fetchProjectById(id);
        return ResponseEntity.ok(getProjectDetailedDto);
    }


    @GetMapping("/all")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<GetProjectSimplifiedDto>> getAllProjects() {
        List<GetProjectSimplifiedDto> projectsDto = projectService.fetchAllProjects();
        return ResponseEntity.ok(projectsDto);
    }

    @GetMapping("/myProjects/{devId}")
    public ResponseEntity<List<GetProjectSimplifiedDto>> getAllProjectsThisUserIsPartOf(@PathVariable("devId") String developerId) {
        List<GetProjectSimplifiedDto> projects = projectService.fetchAllProjectsThisDeveloperIsPartOf(developerId);
        return ResponseEntity.ok(projects);
    }

    @PatchMapping("/update/{projectId}")
    public ResponseEntity<GetProjectDetailedDto> editSingleFieldOfAProject(@PathVariable("projectId") String id, @RequestBody UpdateProjectSingleFieldDto updateProjectSingleFieldDto) throws NoSuchFieldException {
        GetProjectDetailedDto getProjectDetailedDto = projectService.updateSingleFieldOfAProject(id, updateProjectSingleFieldDto);
        return ResponseEntity.ok(getProjectDetailedDto);
    }

    @PatchMapping("/update/team/{projectId}")
    public ResponseEntity<GetProjectDetailedDto> editTeamAssignedToProject(@PathVariable("projectId") String id, @RequestBody UpdateProjectTeamDto updateProjectTeamDto) {
        GetProjectDetailedDto getProjectDetailedDto = projectService.updateTeamAssignedToProject(id, updateProjectTeamDto);
        return ResponseEntity.ok(getProjectDetailedDto);
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable("projectId") String id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
