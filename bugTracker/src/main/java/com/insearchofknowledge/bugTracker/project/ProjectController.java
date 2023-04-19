package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.project.projectDto.AddProjectDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDto;
import com.insearchofknowledge.bugTracker.project.projectDto.UpdateProjectSingleFieldDto;
import com.insearchofknowledge.bugTracker.project.projectDto.UpdateProjectTeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<GetProjectDto> addProject(@RequestBody AddProjectDto addProjectDto) {
        return new ResponseEntity<>(projectService.createNewProject(addProjectDto), HttpStatus.CREATED);
    }

    @GetMapping("{projectId}")
    public ResponseEntity<GetProjectDto> getProjectById(@PathVariable("projectId") String id) {
        GetProjectDto getProjectDto = projectService.fetchProjectById(id);
        return ResponseEntity.ok(getProjectDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetProjectDto>> getAllProjects() {
        List<GetProjectDto> projectsDto = projectService.fetchAllProjects();
        return ResponseEntity.ok(projectsDto);
    }

    @PatchMapping("/update/{projectId}")
    public ResponseEntity<GetProjectDto> editSingleFieldOfAProject(@PathVariable("projectId") String id, @RequestBody UpdateProjectSingleFieldDto updateProjectSingleFieldDto) throws NoSuchFieldException {
        GetProjectDto getProjectDto = projectService.updateSingleFieldOfAProject(id, updateProjectSingleFieldDto);
        return ResponseEntity.ok(getProjectDto);
    }

    @PatchMapping("/update/team/{projectId}")
    public ResponseEntity<GetProjectDto> editTeamAssignedToProject(@PathVariable("projectId") String id, @RequestBody UpdateProjectTeamDto updateProjectTeamDto) {
        GetProjectDto getProjectDto = projectService.updateTeamAssignedToProject(id, updateProjectTeamDto);
        return ResponseEntity.ok(getProjectDto);
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable("projectId") String id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
