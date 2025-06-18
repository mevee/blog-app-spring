package com.vee.Blogapp.services.impl;

import com.vee.Blogapp.entites.Post;
import com.vee.Blogapp.exceptions.ResourceNotFoundException;
import com.vee.Blogapp.payloads.PostDto;
import com.vee.Blogapp.payloads.response.PostResponse;
import com.vee.Blogapp.repositories.CategoryRepository;
import com.vee.Blogapp.repositories.PostRepository;
import com.vee.Blogapp.repositories.UserRepo;
import com.vee.Blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Long userId) {
        var user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        var category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with id " + categoryId + " not found"));
        var object = modelMapper.map(postDto, Post.class);
        object.setImage("test.png");
        var time = LocalDateTime.now();
        object.setCategory(category);
        object.setUser(user);
        object.setCreatedAt(time);
        object.setUpdatedAt(time);
        return modelMapper.map(postRepository.save(object), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        var object = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No post with id " + id + " found."));
        object.setText(postDto.getText());
        object.setTitle(postDto.getTitle());
        object.setImage(postDto.getImage());
        var time = LocalDateTime.now();
        object.setUpdatedAt(time);
        return toPostDto(postRepository.save(object));
    }

    @Override
    public void deletePost(Integer id) {
        System.out.println(">>>>>>>>>>>>>>---deletePost()-start <<<<<<<<<<<<");
        var object = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No post with id " + id + " found."));
        postRepository.delete(object);
        System.out.println(">>>>>>>>>>>>>>---deletePost()-end <<<<<<<<<<<<");
    }

    @Override
    public void deleteAll(List<Integer> id) {
//        for (Integer i : id) {
//            var object = postRepository.findById(i).orElseThrow(() -> new ResourceNotFoundException("No post with id " + i + " found."));
//            postRepository.delete(object);
//        }
    }

    @Override
    public PostDto getPost(Integer id) {
        var object = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No post with id " + id + " found."));
        return toPostDto(object);
    }

    @Override
    public PostResponse getAllPost(Pageable pageable) {
        var page = postRepository.findAll(pageable);
        var posts = page.getContent().stream().map(this::toPostDto).toList();
        return buildResponse(page, posts);
    }

    @Override
    public PostResponse getPostByCategory(Integer categoryId, Pageable pageable) {
        var category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with id " + categoryId + " not found"));
        var page = postRepository.findByCategory(category, pageable);
        var posts = page.getContent().stream().map(this::toPostDto).toList();
        return buildResponse(page, posts);
    }

    @Override
    public PostResponse getPostByUser(Long userId, Pageable pageable) {
        var user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        var page = postRepository.findByUser(user, pageable);
        var posts = page.getContent().stream().map(this::toPostDto).toList();
        return buildResponse(page, posts);

    }

    @Override
    public PostResponse searchPost(String keyword, Pageable pageable) {
        var page = postRepository.findByTitleContaining(keyword,pageable);
        var posts = page.getContent().stream().map(this::toPostDto).toList();
        return buildResponse(page, posts);
    }

    private PostResponse buildResponse(Page<Post> page, List<PostDto> data) {
        PostResponse response = new PostResponse();
        response.setPosts(data);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPage(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;
    }

    private Post toPost(PostDto dto) {
        return modelMapper.map(dto, Post.class);
    }

    private PostDto toPostDto(Post dto) {
        return modelMapper.map(dto, PostDto.class);
    }
}
