package com.insearchofknowledge.bugTracker.comment;

import com.insearchofknowledge.bugTracker.developer.DeveloperDto;
import com.insearchofknowledge.bugTracker.project.ProjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private String id;
    private String content;
    private Date datePosted;
    private DeveloperDto commentAuthor;
    private String commentAuthorId;
    private ProjectDto project;
    private String projectId;
}
