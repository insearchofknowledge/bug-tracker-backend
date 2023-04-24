package com.insearchofknowledge.bugTracker.comment;

import com.insearchofknowledge.bugTracker.comment.commentDto.AddCommentDto;
import com.insearchofknowledge.bugTracker.comment.commentDto.GetCommentDto;
import com.insearchofknowledge.bugTracker.comment.commentDto.UpdateCommentDto;
import com.insearchofknowledge.bugTracker.comment.commentMapper.AddCommentMapper;
import com.insearchofknowledge.bugTracker.comment.commentMapper.GetCommentMapper;
import com.insearchofknowledge.bugTracker.ticket.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final AddCommentMapper addCommentMapper;
    private final GetCommentMapper getCommentMapper;
    private final CommentRepository commentRepository;
    private final TicketService ticketService;

    public GetCommentDto createNewComment(AddCommentDto addCommentDto) {
        Comment comment = addCommentMapper.map(addCommentDto);
        comment.setDatePosted(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        comment.setWasEdited(false);
        return getCommentMapper.map(commentRepository.save(comment));
    }

    public GetCommentDto fetchCommentById(String commentId) throws EntityNotFoundException{
        Comment comment = fetchCommentIfItExists(commentId);
        return getCommentMapper.map(comment);
    }

    public List<GetCommentDto> fetchAllCommentsByTicketId(String ticketId) {
        return ticketService.fetchCommentsForTicket(ticketId).stream().map(getCommentMapper::map).toList();
    }

    public GetCommentDto updateComment(String commentId, UpdateCommentDto updateCommentDto) throws EntityNotFoundException {
        Comment commentToBeUpdated = fetchCommentIfItExists(commentId);
        commentToBeUpdated.setContent(updateCommentDto.getContent());
        commentToBeUpdated.setWasEdited(true);
        return getCommentMapper.map(commentRepository.save(commentToBeUpdated));
    }

    public void deleteComment(String commentId) throws EntityNotFoundException {
        commentRepository.delete(fetchCommentIfItExists(commentId));
    }

    private Comment fetchCommentIfItExists(String commentId) throws EntityNotFoundException{
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            return commentOptional.get();
        } else {
            throw new EntityNotFoundException("Comment with id '" + commentId + "' not found");
        }
    }
}
