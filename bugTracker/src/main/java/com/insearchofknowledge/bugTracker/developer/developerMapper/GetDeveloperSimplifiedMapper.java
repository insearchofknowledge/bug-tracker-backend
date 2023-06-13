package com.insearchofknowledge.bugTracker.developer.developerMapper;

import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.developer.developerDto.GetDeveloperSimplifiedDto;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class GetDeveloperSimplifiedMapper implements Mapper<Developer, GetDeveloperSimplifiedDto> {

    @Override
    public GetDeveloperSimplifiedDto map(Developer developer) {
        return GetDeveloperSimplifiedDto.builder()
                .id(developer.getId())
                .firstName(developer.getFirstName())
                .lastName(developer.getLastName())
                .email(developer.getEmail())
                .phone(developer.getPhone())
                .build();
    }
}
