package com.insearchofknowledge.bugTracker.developer;

import com.insearchofknowledge.bugTracker.generics.Mapper;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class GetDeveloperSimplifiedDtoMapper implements Mapper<Developer, GetDeveloperSimplifiedDto> {

    @Override
    public GetDeveloperSimplifiedDto map(Developer developer) {
        return GetDeveloperSimplifiedDto.builder()
                .firstName(developer.getFirstName())
                .lastName(developer.getLastName())
                .phone(developer.getPhone())
                .email(developer.getEmail())
                .build();
    }
}
