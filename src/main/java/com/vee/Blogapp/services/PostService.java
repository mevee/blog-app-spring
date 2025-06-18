package com.vee.Blogapp.services;

import com.vee.Blogapp.payloads.PostDto;
import com.vee.Blogapp.payloads.response.PostResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer categoryId,Long userId);

    PostDto updatePost(PostDto postDto, Integer id);

    void deletePost(Integer id);

    void deleteAll(List<Integer> id);

    PostDto getPost(Integer id);

    PostResponse getAllPost(Pageable pageable);

    PostResponse getPostByCategory(Integer categoryId, Pageable pageable);

    PostResponse getPostByUser(Long userId, Pageable pageable);

    PostResponse searchPost(String keyword, Pageable pageable);

}
