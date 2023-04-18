package com.insearchofknowledge.bugTracker.developer.developerMapper;

import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.developer.developerDto.AddDeveloperDto;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddDeveloperMapper implements Mapper<AddDeveloperDto, Developer> {

    @Override
    public Developer map(AddDeveloperDto addDeveloperDto) {
        Developer developer = new Developer();
        developer.setFirstName(addDeveloperDto.getFirstName());
        developer.setLastName(addDeveloperDto.getLastName());
        developer.setPhone(addDeveloperDto.getPhone());
        developer.setEmail(addDeveloperDto.getEmail());
        developer.setPassword(addDeveloperDto.getPassword());
        return developer;
    }
}
