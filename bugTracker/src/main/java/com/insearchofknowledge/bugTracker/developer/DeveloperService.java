package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.ticket.Ticket;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Developer> getAllDevelopersByIdList(List<String> developerIds) {
        List<Developer> retrievedDevsList = developerRepository.findAllByIds(developerIds);
        if(retrievedDevsList.size() != developerIds.size()){
            throw new EntityNotFoundException("Some requested Developers couldn't be found. Either Developer with given Id doesn't exist or the Id is wrong");
        }
        return retrievedDevsList;
    }

    public void updateTicketsCreated(String id, Ticket ticket) throws EntityNotFoundException{
        Developer author = getDeveloperIfItExists(id);
        author.getTicketsCreated().add(ticket);
        developerRepository.save(author);
    }

    private Developer getDeveloperIfItExists(String id) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(id);
        if(optionalDeveloper.isPresent()) {
            return optionalDeveloper.get();
        } else {
            throw new EntityNotFoundException("Developer with id '" + id + "' not found");
        }
    }
}
