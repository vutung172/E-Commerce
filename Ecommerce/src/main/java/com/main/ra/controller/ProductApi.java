package com.main.ra.controller;

import com.main.ra.model.dto.ProductDto;
import com.main.ra.model.dto.response.DataResponse;
import com.main.ra.model.dto.response.PageDataResponse;
import com.main.ra.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api.myservice.com/v1/products")
public class ProductApi {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity findAllProduct(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sort
    ){
        Page<ProductDto> list = productService.findAll(page, size, sort);
        PageDataResponse<ProductDto> products = new PageDataResponse<>(list.getContent(), list.getTotalPages(), list.getNumber(),list.getSize(),sort);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/search")
    public ResponseEntity findAllProductByKeyName(
             @RequestParam String productName
    ) {
        List<ProductDto> list = productService.findByProductNameOrDescription(productName);
        return ResponseEntity.ok(new DataResponse<>(list));
    }

}
