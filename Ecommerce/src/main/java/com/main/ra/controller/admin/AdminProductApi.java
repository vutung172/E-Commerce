package com.main.ra.controller.admin;

import com.main.ra.model.dto.ProductDto;
import com.main.ra.model.dto.request.ProductRequest;
import com.main.ra.model.dto.response.DataResponse;
import com.main.ra.model.dto.response.PageDataResponse;
import com.main.ra.model.dto.response.ProductResponse;
import com.main.ra.service.Impl.PageableServiceImpl;
import com.main.ra.service.Impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myservice.com/v1/admin/products")
@Validated
public class AdminProductApi {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private PageableServiceImpl pageableService;

    @PostMapping
    public ResponseEntity addNewProduct(
            @RequestBody @Valid ProductRequest request
    ){
        ProductDto product = productService.addProduct(request);
        ProductResponse<ProductDto> data = new ProductResponse<>();
        data.getProducts().add(product);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductRequest request
    ){
        ProductDto productDto = productService.updateProduct(id,request);
        DataResponse<ProductDto> response = new DataResponse<>();
        response.getData().add(productDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity findAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sort
    ){
        Page<ProductDto> list = productService.findAll(page,size,sort);
        PageDataResponse<ProductDto> products = new PageDataResponse<>(list.getContent(), list.getTotalPages(), list.getNumber(),list.getSize(),sort);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity findById(
            @PathVariable Long productId
    ){
        ProductDto productDto = productService.findById(productId);
        DataResponse<ProductDto> response = new DataResponse<>();
        response.getData().add(productDto);
        return ResponseEntity.ok(response);
    }

}
