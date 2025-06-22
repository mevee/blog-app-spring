package com.vee.Blogapp.controllers;

import com.vee.Blogapp.config.AppConstants;
import com.vee.Blogapp.payloads.CommentDto;
import com.vee.Blogapp.payloads.ResponsePayload;
import com.vee.Blogapp.payloads.ResponsePayloadData;
import com.vee.Blogapp.services.CommentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comment")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentDto request, @PathVariable Long userId, @PathVariable int postId) {
        logger.info("Create comment request: {}", request);
        CommentDto userDto = commentService.createComment(userId, postId, request);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 201, userDto));
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<?> updateComment(@Valid @RequestBody CommentDto request, @PathVariable Integer commentId) {
        logger.info("Update comment request: {}", request);
        CommentDto userDto = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 201, userDto));
    }

    @GetMapping("/post/{postId}/comment")
    public ResponseEntity<?> getPostByComment(@PathVariable int postId, @RequestParam(defaultValue = AppConstants.dfPage) int page, @RequestParam(defaultValue = AppConstants.dfPageSize) int size, @RequestParam(defaultValue = AppConstants.dfPage) int catId, @RequestParam(defaultValue = AppConstants.dfSortBy) String sortBy) {
        logger.info("get all comemnt of post = {}, body = {}", postId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<CommentDto> postList = commentService.getAllCommentBy(postId);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, postList));
    }


    @GetMapping("/comment/all")
    public ResponseEntity<ResponsePayload> getAllComment(@RequestParam(defaultValue = AppConstants.dfPage) int page, @RequestParam(defaultValue = AppConstants.dfPageSize) int size, @RequestParam(defaultValue = "0") int catId, @RequestParam(defaultValue = AppConstants.dfSortBy) String sortBy) {
        logger.info("Fetch all comment");
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<CommentDto> postResponse = commentService.getAllComment();
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, postResponse));
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<ResponsePayload> getComment(@PathVariable Integer id) {
        logger.info("get Comment by ID: {}", id);
        var post = commentService.getComment(id);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, post));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ResponsePayload> deleteComment(@PathVariable Integer id) {
        logger.info("Delete comment by ID: {}", id);
        commentService.deleteComment(id);
        return ResponseEntity.ok(ResponsePayload.success("Comment deleted success."));
    }

}
