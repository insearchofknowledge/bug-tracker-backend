package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.developer.developerDto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping("/create")
    public ResponseEntity<GetDeveloperDto> addDeveloper(@RequestBody @Valid AddDeveloperDto addDeveloperDto) {
        GetDeveloperDto getDeveloperDto = developerService.createNewDeveloper(addDeveloperDto);
        return ResponseEntity.ok(getDeveloperDto);
    }

    @GetMapping("/profile")
    public ResponseEntity<GetDeveloperDto> getDeveloperByToken(HttpServletRequest request) {
//        String jwt = request.getHeader("Authorization").substring(7);
        GetDeveloperDto getDeveloperDto = developerService.fetchDeveloperByToken(request);
        return ResponseEntity.ok(getDeveloperDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetDeveloperSimplifiedDto>> getAllDevelopers() {
        List<GetDeveloperSimplifiedDto> listOfDeveloperDto = developerService.fetchAllDevelopers();
        return ResponseEntity.ok(listOfDeveloperDto);
    }

    @PatchMapping("/update/{developerId}")
    public ResponseEntity<GetDeveloperDto> editSingleFieldOfADeveloper(@PathVariable("developerId") String id, @RequestBody UpdateDeveloperSingleFieldDto updateDeveloperSingleFieldDto) throws NoSuchFieldException {
        GetDeveloperDto editedDeveloper = developerService.updateSingleFieldOfADeveloper(id, updateDeveloperSingleFieldDto);
        return ResponseEntity.ok(editedDeveloper);
    }

    @PatchMapping("/update/fields/{developerId}")
    public ResponseEntity<GetDeveloperDto> editMultipleFieldsOfADeveloper(@PathVariable("developerId") String id, @RequestBody UpdateDeveloperMultipleFieldsDto updateDeveloperMultipleFieldsDto) {
        GetDeveloperDto editedDeveloper = developerService.updateMultipleFieldsOfADeveloper (id, updateDeveloperMultipleFieldsDto);
        return ResponseEntity.ok(editedDeveloper);
    }

    @DeleteMapping("/delete/{developerId}")
    public ResponseEntity<?> removeDeveloper(@PathVariable("developerId") String id) {
        developerService.deleteDeveloperById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
