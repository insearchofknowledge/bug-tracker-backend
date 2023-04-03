package com.insearchofknowledge.bugTracker.comment;

import com.insearchofknowledge.bugTracker.developer.DeveloperMapper;
import com.insearchofknowledge.bugTracker.developer.DeveloperRepository;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import com.insearchofknowledge.bugTracker.project.ProjectMapper;
import com.insearchofknowledge.bugTracker.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentMapper implements Mapper<Comment, CommentDto> {

    private final DeveloperMapper developerMapper;
    private final ProjectMapper projectMapper;
    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;

    @Override
    public CommentDto convertToDto(Comment entity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent(entity.getContent());
        commentDto.setCommentAuthor(developerMapper.convertToDto(entity.getCommentAuthor()));
        commentDto.setProject(projectMapper.convertToDto(entity.getProject()));
        return commentDto;
    }

    @Override
    public Comment convertToEntity(CommentDto dto) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCommentAuthor(developerRepository.getReferenceById(dto.getCommentAuthorId()));
        comment.setProject(projectRepository.getReferenceById(dto.getProjectId()));
        return comment;
    }
}
