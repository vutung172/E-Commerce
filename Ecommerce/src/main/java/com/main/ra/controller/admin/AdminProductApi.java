package com.main.ra.controller.admin;

import com.main.ra.model.dto.ProductDto;
import com.main.ra.model.dto.request.ProductRequest;
import com.main.ra.model.dto.response.DataResponse;
import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.model.dto.response.PageDataResponse;
import com.main.ra.model.dto.response.ProductResponse;
import com.main.ra.service.Impl.PageableServiceImpl;
import com.main.ra.service.Impl.ProductServiceImpl;
import com.main.ra.util.MessageLoader;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api.myservice.com/v1/admin/products")
@Validated
public class AdminProductApi {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private PageableServiceImpl pageableService;
    @Autowired
    private MessageLoader loader;

    @PostMapping(consumes = {"multipart/form-data","application/*", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity addNewProduct(
            @RequestPart@Valid ProductRequest request,
            @RequestPart("file") MultipartFile file
    ){
        request.setImage(file);
        ProductDto product = productService.addProduct(request);
        ProductResponse<ProductDto> data = new ProductResponse<>();
        data.getProducts().add(product);
        return ResponseEntity.ok(data);
    }

    @PutMapping(value = "/{id}",consumes = {"multipart/form-data","application/*",MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity updateProduct(
            @PathVariable Long id,
            @RequestPart @Valid ProductRequest request,
            @RequestPart("file") MultipartFile file
    ){
        request.setImage(file);
        ProductDto productDto = productService.updateProduct(id,request);
        DataResponse<ProductDto> response = new DataResponse<>();
        response.getData().add(productDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(
        @PathVariable Long id
    ){
        if (productService.deleteProduct(id)){
            return ResponseEntity.ok(new MessageResponse(loader.getMessage("success.DeleteProduct")));
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
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
