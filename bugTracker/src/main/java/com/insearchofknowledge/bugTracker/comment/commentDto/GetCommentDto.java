package com.insearchofknowledge.bugTracker.comment.commentDto;

import com.insearchofknowledge.bugTracker.developer.developerDto.GetDeveloperSimplifiedDto;
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
    private Boolean wasEdited;
    private GetDeveloperSimplifiedDto commentAuthor;
}
