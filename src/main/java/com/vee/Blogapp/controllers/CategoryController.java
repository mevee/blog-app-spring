package com.vee.Blogapp.controllers;

import com.vee.Blogapp.entites.PostCategory;
import com.vee.Blogapp.payloads.CategoryDto;
import com.vee.Blogapp.payloads.ResponsePayload;
import com.vee.Blogapp.payloads.ResponsePayloadData;
import com.vee.Blogapp.payloads.request.CategoryRequest;
import com.vee.Blogapp.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @PostMapping("/")
    ResponseEntity<?> createCategory(@Valid  @RequestBody CategoryDto request){
        request.setId(0);
        CategoryDto category =  service.createCategory(request);
        var payload = ResponsePayloadData.success();
        payload.setData(category);
        return ResponseEntity.ok(payload);
    }

    @PutMapping("/")
    ResponseEntity<?> updateCategory(@Valid  @RequestBody CategoryDto request){
        CategoryDto category =  service.updateCategory(request);
        var payload = ResponsePayloadData.success();
        payload.setData(category);
        return ResponseEntity.ok(payload);
    }
    @GetMapping("/{categoryId}")
    ResponseEntity<?> getCategoryById(@PathVariable int categoryId){
        CategoryDto category =  service.getCategory(categoryId);
        var payload = ResponsePayloadData.success();
        payload.setData(category);
        return ResponseEntity.ok(payload);
    }
    @DeleteMapping("/{categoryId}")
    ResponseEntity<?> deleteCategoryById(@PathVariable int categoryId){
        boolean category =  service.deleteCategory(categoryId);
        var payload = ResponsePayload.success();
        return ResponseEntity.ok(payload);
    }

    @GetMapping("/")
    ResponseEntity<?> allCategory(){
        List<CategoryDto> category =  service.getAllCategory();
        var payload = ResponsePayloadData.success();
        payload.setData(category);
        return ResponseEntity.ok(payload);
    }

}
