package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.developer.developerDto.AddDeveloperDto;
import com.insearchofknowledge.bugTracker.developer.developerDto.GetDeveloperDto;
import com.insearchofknowledge.bugTracker.developer.developerDto.UpdateDeveloperMultipleFieldsDto;
import com.insearchofknowledge.bugTracker.developer.developerDto.UpdateDeveloperSingleFieldDto;
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

    @GetMapping("/{developerId}")
    public ResponseEntity<GetDeveloperDto> getDeveloperById(@PathVariable("developerId") String id) {
        GetDeveloperDto getDeveloperDto = developerService.fetchDeveloperById(id);
        return ResponseEntity.ok(getDeveloperDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetDeveloperDto>> getAllDevelopers() {
        List<GetDeveloperDto> listOfDeveloperDto = developerService.fetchAllDevelopers();
        return ResponseEntity.ok(listOfDeveloperDto);
    }

    @PostMapping("/create")
    public ResponseEntity<GetDeveloperDto> createDeveloper(@RequestBody @Valid AddDeveloperDto addDeveloperDto) {
        GetDeveloperDto getDeveloperDto = developerService.addNewDeveloper(addDeveloperDto);
        return ResponseEntity.ok(getDeveloperDto);
    }

    @PatchMapping("/update/{developerId}")
    public ResponseEntity<GetDeveloperDto> editSingleFieldOfADeveloper(@PathVariable("developerId") String id, @RequestBody UpdateDeveloperSingleFieldDto updateDeveloperSingleFieldDto) throws NoSuchFieldException{
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
