package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.developer.developerDto.*;
import com.insearchofknowledge.bugTracker.developer.developerMapper.AddDeveloperMapper;
import com.insearchofknowledge.bugTracker.developer.developerMapper.GetDeveloperMapper;
import com.insearchofknowledge.bugTracker.developer.developerMapper.GetDeveloperSimplifiedMapper;
import com.insearchofknowledge.bugTracker.project.Project;
import com.insearchofknowledge.bugTracker.ticket.Ticket;
import com.insearchofknowledge.bugTracker.token.Token;
import com.insearchofknowledge.bugTracker.token.TokenRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeveloperService {

    private final DeveloperRepository developerRepository;
    private final GetDeveloperMapper getDeveloperMapper;
    private final GetDeveloperSimplifiedMapper getDeveloperSimplifiedMapper;
    private final AddDeveloperMapper addDeveloperMapper;
    private final TokenRepository tokenRepository;

    public GetDeveloperDto createNewDeveloper(AddDeveloperDto addDeveloperDto) {
        Developer newDeveloper = addDeveloperMapper.map(addDeveloperDto);
        newDeveloper.setRole(Role.ROLE_DEVELOPER);
        return getDeveloperMapper.map(developerRepository.save(newDeveloper));
    }

    public GetDeveloperDto fetchDeveloperById(String id) {
        return getDeveloperMapper.map(fetchDeveloperIfItExists(id));
    }

    public GetDeveloperDto fetchDeveloperByToken(HttpServletRequest request) {
        String developerId = getDeveloperIdBasedOnToken(request);
        return getDeveloperMapper.map(fetchDeveloperIfItExists(developerId));
    }

    public List<GetDeveloperSimplifiedDto> fetchAllDevelopers() {
        return developerRepository.findAll().stream().map(getDeveloperSimplifiedMapper::map).toList();
    }

    public List<Developer> fetchAllDevelopersByIdList(List<String> developerIds) {
        List<Developer> retrievedDevsList = developerRepository.findAllByIds(developerIds);
        if(retrievedDevsList.size() != developerIds.size()){
            throw new EntityNotFoundException("Some requested Developers couldn't be found. Either Developer with given Id doesn't exist or the Id is wrong");
        }
        return retrievedDevsList;
    }

    public GetDeveloperDto updateSingleFieldOfADeveloper(String developerId, UpdateDeveloperSingleFieldDto updateDeveloperSingleFieldDto) throws NoSuchFieldException {
        Developer developerToBeUpdated = fetchDeveloperIfItExists(developerId);
        switch (updateDeveloperSingleFieldDto.getFieldName()) {
            case "firstName":
                developerToBeUpdated.setFirstName(updateDeveloperSingleFieldDto.getFieldValue());
                break;
            case "lastName":
                developerToBeUpdated.setLastName(updateDeveloperSingleFieldDto.getFieldValue());
                break;
            case "phone":
                developerToBeUpdated.setPhone(updateDeveloperSingleFieldDto.getFieldValue());
                break;
            case "email":
                developerToBeUpdated.setEmail(updateDeveloperSingleFieldDto.getFieldValue());
                break;
            default:
                throw new NoSuchFieldException("Invalid field name or specified field is not to be modified.");
        }
        return getDeveloperMapper.map(developerRepository.save(developerToBeUpdated));
    }

    public GetDeveloperDto updateMultipleFieldsOfADeveloper(String developerId, UpdateDeveloperMultipleFieldsDto updateDeveloperMultipleFieldsDto) {
        Developer developerToBeUpdated = fetchDeveloperIfItExists(developerId);
        developerToBeUpdated.setFirstName(updateDeveloperMultipleFieldsDto.getFirstName());
        developerToBeUpdated.setLastName(updateDeveloperMultipleFieldsDto.getLastName());
        developerToBeUpdated.setPhone(updateDeveloperMultipleFieldsDto.getPhone());
        developerToBeUpdated.setEmail(updateDeveloperMultipleFieldsDto.getEmail());
        return getDeveloperMapper.map(developerRepository.save(developerToBeUpdated));
    }

    // When adding a new ticket it also needs to be added to the list of tickets created by the author
    public void updateTicketsCreated(String authorId, Ticket ticket) throws EntityNotFoundException {
        Developer author = fetchDeveloperIfItExists(authorId);
        author.getTicketsCreated().add(ticket);
        developerRepository.save(author);
    }

    // When creating a new project or when assigning a developer to a project - the projects field of that developer should also be updated
    // thanks to the awesomeness of Spring Boot this method is redundat
    // if the mapping is set up as it should be this update is performed automatically
    public void updateProjects(String developerId, Project project) throws EntityNotFoundException {
        Developer developer = fetchDeveloperIfItExists(developerId);
        developer.getProjects().add(project);
        developerRepository.save(developer);
    }

    public void deleteDeveloperById(String developerId) {
        developerRepository.delete(fetchDeveloperIfItExists(developerId));
    }

    public void checkIfDeveloperIdExists(String idToBeChecked) {
       if(!developerRepository.existsById(idToBeChecked)) {
           throw new EntityNotFoundException("Developer with id '" + idToBeChecked + "' not found");
       }
    }

    private Developer fetchDeveloperIfItExists(String id) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(id);
        if(optionalDeveloper.isPresent()) {
            return optionalDeveloper.get();
        } else {
            throw new EntityNotFoundException("Developer with id '" + id + "' not found");
        }
    }

    public String getDeveloperIdBasedOnToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization").substring(7);
        String developerId = tokenRepository.findByToken(jwt).get().getDeveloper().getId();
        return developerId;
    }
}
