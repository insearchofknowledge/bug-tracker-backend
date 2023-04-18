package com.insearchofknowledge.bugTracker.project;

import com.insearchofknowledge.bugTracker.project.projectDto.AddProjectDto;
import com.insearchofknowledge.bugTracker.project.projectDto.GetProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<GetProjectDto> addProject(@RequestBody AddProjectDto addProjectDto) {
        return new ResponseEntity<>(projectService.createProject(addProjectDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable("projectId") String id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
