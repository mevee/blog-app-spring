package com.vee.Blogapp.services.impl;

import com.vee.Blogapp.entites.Comment;
import com.vee.Blogapp.entites.Post;
import com.vee.Blogapp.entites.User;
import com.vee.Blogapp.exceptions.ResourceNotFoundException;
import com.vee.Blogapp.payloads.CommentDto;
import com.vee.Blogapp.payloads.response.CommentResponse;
import com.vee.Blogapp.repositories.CommentRepo;
import com.vee.Blogapp.repositories.PostRepository;
import com.vee.Blogapp.repositories.UserRepo;
import com.vee.Blogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepository postRepo;
    @Autowired
    ModelMapper modelMapper;

    private CommentDto commentToDto(Comment user) {
        return modelMapper.map(user, CommentDto.class);
    }

    private Comment dtoToComment(CommentDto user) {
        return modelMapper.map(user, Comment.class);
    }

    @Override
    public CommentDto createComment(Long userId,Integer postId,CommentDto dto) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id:"+userId+" not found"));
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with id:"+postId+" not found"));
        Comment comment = dtoToComment(dto);
        comment.setPost(post);
//        comment.setUser(user);
        Comment result = commentRepo.save(comment);
        return this.commentToDto(result);
    }

    @Override
    public CommentDto updateComment(Integer commentId, CommentDto dto) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found with Id: " + commentId));
        comment.setComment(dto.getComment());
        comment.setUpdatedAt(LocalDateTime.now());
        Comment result = commentRepo.save(comment);
        return this.commentToDto(result);
    }

    @Override
    public CommentDto getComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found with Id: " + commentId));
        return this.commentToDto(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found with Id: " + commentId));
        commentRepo.delete(comment);
    }

    @Override
    public List<CommentDto> getAllCommentBy(Integer postId) {
        List<Comment> comment = commentRepo.findByPostId(postId);
        return comment.stream().map(this::commentToDto).toList();
    }

    @Override
    public CommentResponse getAllCommentByUser(Long userId, Pageable pageable) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id:"+userId+" not found"));
//        Page<Comment> page = commentRepo.findByUserId(user,pageable);
//        List<CommentDto> data = page.stream().map(this::commentToDto).toList();
        CommentResponse response = new CommentResponse();
//        response.setPosts(data);
//        response.setPageNumber(page.getNumber());
//        response.setPageSize(page.getSize());
//        response.setTotalElements(page.getTotalElements());
//        response.setTotalPage(page.getTotalPages());
//        response.setLastPage(page.isLast());
        return response;
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> comment = commentRepo.findAll();
        return comment.stream().map(this::commentToDto).toList();
    }
}
