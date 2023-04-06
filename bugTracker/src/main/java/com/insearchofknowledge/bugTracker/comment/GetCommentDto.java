package com.insearchofknowledge.bugTracker.comment;

import com.insearchofknowledge.bugTracker.developer.GetDeveloperDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GetCommentDto {

    private String id;
    private String content;
    private LocalDateTime datePosted;
    private GetDeveloperDto commentAuthor;
}
