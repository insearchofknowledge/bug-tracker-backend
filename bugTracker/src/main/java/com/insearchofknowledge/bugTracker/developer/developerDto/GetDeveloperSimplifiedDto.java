package com.insearchofknowledge.bugTracker.developer.developerDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetDeveloperSimplifiedDto {

    // This DTO is meant to be used for displaying the authors and the devs assigned to a ticket
    // In order to prevent StackOverflow / infinite loops with developer and their tickets and so on
    // Also there is no need to display all information about the developer in the ticket details -> prevents over-fetching

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
