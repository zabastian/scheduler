package org.example.scheduler.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.comment.model.dto.CommentDto;
import org.example.scheduler.comment.model.request.CreateCommentRequest;
import org.example.scheduler.comment.model.request.UpdateCommentRequest;
import org.example.scheduler.comment.repository.CommentRepository;
import org.example.scheduler.common.entity.Comment;
import org.example.scheduler.common.entity.Schedule;
import org.example.scheduler.common.entity.User;
import org.example.scheduler.common.exception.ValidateException;
import org.example.scheduler.schedule.repository.ScheduleRepository;
import org.example.scheduler.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentDto getComment(long commentId) {
        Comment comment = findCommentById(commentId);
        return convertDto(comment);
    }

    public CommentDto createComment(CreateCommentRequest request, long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ValidateException("존재 하지 않는 유저 입니다.", HttpStatus.NOT_FOUND));
        Schedule schedule = scheduleRepository.findById(request.scheduleId()).orElseThrow(()-> new ValidateException("존재 하지 않는 게시글 입니다.", HttpStatus.NOT_FOUND));
        Comment comment = Comment.of(request,user,schedule);
        commentRepository.save(comment);
        return convertDto(comment);
    }

    public CommentDto updateComment(long commentId, UpdateCommentRequest request) {
        Comment comment = findCommentById(commentId);
        comment.updateComment(request);
        commentRepository.save(comment);
        return convertDto(comment);
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

    private Comment findCommentById(long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(()-> new ValidateException("존재 하지 않는 댓글 입니다.", HttpStatus.NOT_FOUND));
    }

    private CommentDto convertDto(Comment comment) {return CommentDto.convertDto(comment); }
}
