package com.insearchofknowledge.bugTracker.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCommentDto {

    private String content;
    private String commentAuthor;
    private String ticket;

    // Date Posted will be set in the service layer
}
