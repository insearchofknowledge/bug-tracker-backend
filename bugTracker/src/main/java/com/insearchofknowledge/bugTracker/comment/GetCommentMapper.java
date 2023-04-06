package com.insearchofknowledge.bugTracker.comment;

import com.insearchofknowledge.bugTracker.developer.GetDeveloperMapper;
import com.insearchofknowledge.bugTracker.generics.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCommentMapper implements Mapper<Comment, GetCommentDto> {

    private final GetDeveloperMapper getDeveloperMapper;

    @Override
    public GetCommentDto map(Comment entity) {
        GetCommentDto getCommentDto = new GetCommentDto();
        getCommentDto.setId(entity.getId());
        getCommentDto.setContent(entity.getContent());
        getCommentDto.setDatePosted(entity.getDatePosted());
        getCommentDto.setCommentAuthor(getDeveloperMapper.map(entity.getCommentAuthor()));
        return getCommentDto;
    }
}
