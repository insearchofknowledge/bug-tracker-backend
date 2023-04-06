package com.insearchofknowledge.bugTracker.comment;

import com.insearchofknowledge.bugTracker.developer.DeveloperRepository;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCommentMapper implements Mapper<AddCommentDto, Comment> {

    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Comment map(AddCommentDto addCommentDto) {
        Comment comment = new Comment();
        comment.setContent(addCommentDto.getContent());
        comment.setCommentAuthor(developerRepository.getReferenceById(addCommentDto.getCommentAuthor()));
        comment.setProject(projectRepository.getReferenceById(addCommentDto.getProject()));
        return comment;
    }
}
