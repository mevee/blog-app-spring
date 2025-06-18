package com.vee.Blogapp.controllers;

import com.vee.Blogapp.config.AppConstants;
import com.vee.Blogapp.payloads.PostDto;
import com.vee.Blogapp.payloads.ResponsePayload;
import com.vee.Blogapp.payloads.ResponsePayloadData;
import com.vee.Blogapp.payloads.response.PostResponse;
import com.vee.Blogapp.services.FileService;
import com.vee.Blogapp.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto request, @PathVariable Long userId, @PathVariable int categoryId) {
        logger.info("Create post request: {}", request);
        PostDto userDto = postService.createPost(request, categoryId, userId);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 201, userDto));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> updatePost(@Valid @RequestBody PostDto request, @PathVariable Integer postId) {
        logger.info("Update post request: {}", request);
        PostDto userDto = postService.updatePost(request, postId);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 201, userDto));
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<ResponsePayload> getPostByCategory(@PathVariable int categoryId, @RequestParam(defaultValue = AppConstants.dfPage) int page, @RequestParam(defaultValue = AppConstants.dfPageSize) int size, @RequestParam(defaultValue = AppConstants.dfPage) int catId, @RequestParam(defaultValue = AppConstants.dfSortBy) String sortBy) {
        logger.info("get all post by category = {}, body = {}", categoryId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        PostResponse postList = postService.getPostByCategory(categoryId, pageable);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, postList));
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<ResponsePayload> getPostByUser(@PathVariable Long userId, @RequestParam(defaultValue = AppConstants.dfPage) int page, @RequestParam(defaultValue = AppConstants.dfPageSize) int size, @RequestParam(defaultValue = "0") int catId, @RequestParam(defaultValue = AppConstants.dfSortBy) String sortBy) {
        logger.info("get all post by user id = {}, body = {}", userId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        PostResponse postList = postService.getPostByUser(userId, pageable);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, postList));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponsePayload> getAllPost(@RequestParam(defaultValue = AppConstants.dfPage) int page, @RequestParam(defaultValue = AppConstants.dfPageSize) int size, @RequestParam(defaultValue = "0") int catId, @RequestParam(defaultValue = AppConstants.dfSortBy) String sortBy) {
        logger.info("Fetch all post");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        PostResponse postResponse = postService.getAllPost(pageable);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, postResponse));
    }

    @GetMapping("/posts/search")
    public ResponseEntity<ResponsePayload> searchPost(@RequestParam(defaultValue = "") String query, @RequestParam(defaultValue = AppConstants.dfPage) int page, @RequestParam(defaultValue = AppConstants.dfPageSize) int size, @RequestParam(defaultValue = "0") int catId, @RequestParam(defaultValue = AppConstants.dfSortBy) String sortBy) {
        logger.info("Post by Search keyword");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        PostResponse users = postService.searchPost(query, pageable);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, users));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<ResponsePayload> getPost(@PathVariable Integer postId) {
        logger.info("getPost by ID: {}", postId);
        var post = postService.getPost(postId);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, post));
    }

//    @DeleteMapping("/posts/{postId}")
//    public ResponseEntity<ResponsePayload> deletePost(@PathVariable Integer postId) {
//        logger.info("Delete pos by ID: {}", postId);
////        postService.deletePost(postId);
//        return ResponseEntity.ok(ResponsePayload.success("Post deleted success."));
//    }

    @PostMapping("/posts/{postId}/image")
    public ResponseEntity<?> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {
        logger.info("uploadPostImage: {}", image);
        var post = postService.getPost(postId);
        var fileName = fileService.uploadImage(path, image);
        post.setImage(fileName);
        PostDto userDto = postService.updatePost(post, postId);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 201, userDto));
    }

    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadPostImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        logger.info("downloadPostImage : {}", imageName);
        var resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
