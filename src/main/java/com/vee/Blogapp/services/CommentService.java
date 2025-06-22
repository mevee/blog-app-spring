package com.vee.Blogapp.services;

import com.vee.Blogapp.payloads.CommentDto;
import com.vee.Blogapp.payloads.response.CommentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long userId,Integer postId,CommentDto dto);
    CommentDto updateComment(Integer commentId,CommentDto dto);
    CommentDto getComment(Integer commentId);
    void deleteComment(Integer commentId);
    List<CommentDto> getAllCommentBy(Integer postId);
    CommentResponse getAllCommentByUser(Long userId, Pageable pageable);
    List<CommentDto> getAllComment();
}
