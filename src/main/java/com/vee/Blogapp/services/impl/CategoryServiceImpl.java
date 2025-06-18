package com.vee.Blogapp.services.impl;

import com.vee.Blogapp.entites.PostCategory;
import com.vee.Blogapp.exceptions.ResourceNotFoundException;
import com.vee.Blogapp.payloads.CategoryDto;
import com.vee.Blogapp.repositories.CategoryRepository;
import com.vee.Blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository service;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto category) {
        PostCategory postCategory = service.save(toPostCategory(category));
        return toCategoryDto(postCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto category) {
        var postCategory = service.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("User with id " + category.getId() + " not found."));
        postCategory.setName(category.getName());
        postCategory.setDescription(category.getDescription());
        return toCategoryDto(service.save(postCategory));
    }

    @Override
    public boolean deleteCategory(int id) {
        var postCategory = service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not exist."));
        service.delete(postCategory);
        return true;
    }

    @Override
    public CategoryDto getCategory(int id) {
        var postCategory = service.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found."));
        return toCategoryDto(postCategory);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<PostCategory> categoryList = service.findAll();
        return categoryList.stream().map(this::toCategoryDto).toList();
    }

    private CategoryDto toCategoryDto(PostCategory obj) {
        return modelMapper.map(obj, CategoryDto.class);
    }

    private PostCategory toPostCategory(CategoryDto obj) {
        return modelMapper.map(obj, PostCategory.class);
    }
}
