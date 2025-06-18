package com.vee.Blogapp.services;

import com.vee.Blogapp.entites.PostCategory;
import com.vee.Blogapp.payloads.CategoryDto;
import com.vee.Blogapp.payloads.UserDto;

import java.util.List;
import java.util.Locale;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto category);
    CategoryDto updateCategory(CategoryDto category);
    CategoryDto getCategory(int id);
    boolean deleteCategory(int id);

    List<CategoryDto> getAllCategory();

}
