package com.insearchofknowledge.bugTracker.comment.commentDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCommentDto {

    @NotNull
    @NotBlank
    private String content;
    @NotNull
    @NotBlank
    private String ticket;

    // Date Posted will be set in the service layer
    // Author will be set in the service layer
}
