package com.insearchofknowledge.bugTracker.comment.commentDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentDto {

    @NotNull(message = "Comment field can't be null")
    @NotBlank(message = "Comment field can't be blank")
    private String content;
}
