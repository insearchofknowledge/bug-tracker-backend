package com.insearchofknowledge.bugTracker.comment;

import com.insearchofknowledge.bugTracker.comment.commentDto.AddCommentDto;
import com.insearchofknowledge.bugTracker.comment.commentDto.GetCommentDto;
import com.insearchofknowledge.bugTracker.comment.commentDto.UpdateCommentDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<GetCommentDto> addComment(@RequestBody AddCommentDto addCommentDto, HttpServletRequest request) {
        GetCommentDto newComment = commentService.createNewComment(addCommentDto, request);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("{commentId}")
    public ResponseEntity<GetCommentDto> getCommentById(@PathVariable("commentId") String id) {
        GetCommentDto commentDto = commentService.fetchCommentById(id);
        return ResponseEntity.ok(commentDto);
    }

    @GetMapping("/all/{ticketId}")
    public ResponseEntity<List<GetCommentDto>> getAllCommentsRelatedToTicket(@PathVariable("ticketId") String ticketId) {
        List<GetCommentDto> comments = commentService.fetchAllCommentsByTicketId(ticketId);
        return ResponseEntity.ok(comments);
    }

    @PatchMapping("/update/{commentId}")
    public ResponseEntity<GetCommentDto> editComment(@PathVariable("commentId") String commentId, @RequestBody @Valid UpdateCommentDto updateCommentDto) {
        GetCommentDto commentDto = commentService.updateComment(commentId,updateCommentDto);
        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<?> removeComment(@PathVariable("commentId") String commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
