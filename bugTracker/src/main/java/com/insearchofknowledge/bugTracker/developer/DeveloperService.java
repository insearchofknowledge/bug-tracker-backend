package com.insearchofknowledge.bugTracker.developer;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
