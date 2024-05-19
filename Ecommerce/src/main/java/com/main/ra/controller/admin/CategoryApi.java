package com.main.ra.controller.admin;

import com.main.ra.model.dto.CategoryDto;
import com.main.ra.model.dto.request.CategoryRequest;
import com.main.ra.model.dto.response.DataResponse;
import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.model.dto.response.PageDataResponse;
import com.main.ra.model.dto.response.ProductResponse;
import com.main.ra.service.Impl.CategoryServiceImpl;
import com.main.ra.util.MessageLoader;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myservice.com/v1/admin/categories")
@Validated
public class CategoryApi {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private MessageLoader loader;

    @PostMapping
    public ResponseEntity addNewCategory(
            @RequestBody @Valid CategoryRequest request
    ){
        CategoryDto categoryDto = categoryService.add(request);
        ProductResponse<CategoryDto> data = new ProductResponse<>();
        data.getProducts().add(categoryDto);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CategoryRequest request
    ){
        CategoryDto categoryDto = categoryService.update(id,request);
        DataResponse<CategoryDto> data = new DataResponse<>();
        data.getData().add(categoryDto);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(
            @PathVariable Long id
    ){
        categoryService.delete(id);
        return ResponseEntity.ok(new MessageResponse(loader.getMessage("success.DeleteCategory")));
    }

    @GetMapping
    public ResponseEntity findAll(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestParam String sort
    ){
        Page<CategoryDto> list = categoryService.findAll(page,size,sort);
        PageDataResponse<CategoryDto> products = new PageDataResponse<>(list.getContent(),
                list.getTotalPages(),
                list.getNumber(),
                list.getSize(),
                sort);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(
            @PathVariable Long id
    ){
        CategoryDto categoryDto = categoryService.findById(id);
        DataResponse<CategoryDto> response = new DataResponse<>();
        response.getData().add(categoryDto);
        return ResponseEntity.ok(response);
    }
}
