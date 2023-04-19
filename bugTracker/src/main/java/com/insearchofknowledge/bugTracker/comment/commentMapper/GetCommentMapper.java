package com.insearchofknowledge.bugTracker.comment.commentMapper;

import com.insearchofknowledge.bugTracker.comment.Comment;
import com.insearchofknowledge.bugTracker.comment.commentDto.GetCommentDto;
import com.insearchofknowledge.bugTracker.developer.developerMapper.GetDeveloperSimplifiedDtoMapper;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCommentMapper implements Mapper<Comment, GetCommentDto> {

    private final GetDeveloperSimplifiedDtoMapper getDeveloperSimplifiedDtoMapper;

    @Override
    public GetCommentDto map(Comment entity) {
        GetCommentDto getCommentDto = new GetCommentDto();
        getCommentDto.setId(entity.getId());
        getCommentDto.setContent(entity.getContent());
        getCommentDto.setDatePosted(entity.getDatePosted());
        getCommentDto.setWasEdited(entity.getWasEdited());
        getCommentDto.setCommentAuthor(getDeveloperSimplifiedDtoMapper.map(entity.getCommentAuthor()));
        return getCommentDto;
    }
}
