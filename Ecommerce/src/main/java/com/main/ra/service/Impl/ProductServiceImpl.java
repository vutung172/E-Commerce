package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.exception.PageableDtoException;
import com.main.ra.model.dto.ProductDto;
import com.main.ra.model.dto.request.PageableRequest;
import com.main.ra.model.dto.request.ProductRequest;
import com.main.ra.model.entity.ProductEntity;
import com.main.ra.repository.ProductRepository;
import com.main.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MapperUtilServiceImpl mapper;
    @Autowired
    private PageableServiceImpl pageableService;


    public ProductDto addProduct(ProductRequest request) {
        if (productRepository.findProductEntitiesByProductName(request.getProductName()) == null) {
            ProductEntity product = mapper.convertDTOToEntity(request, ProductEntity.class);
            ProductEntity productDB = productRepository.save(product);
            System.out.println(productDB.getSku());
            return mapper.convertEntityToDTO(productDB, ProductDto.class);
        } else {
            throw new BaseException("exception.ProductExisted", HttpStatus.FORBIDDEN);
        }
    }
    public ProductDto updateProduct(Long id, ProductRequest request){
        ProductEntity product = productRepository.findById(id).orElse(null);
        if (product != null){
            ProductEntity updatedProduct = mapper.updateToEntity(request,product);
            ProductEntity productDB = productRepository.save(updatedProduct);
            return mapper.convertEntityToDTO(productDB, ProductDto.class);
        }else {
            throw new BaseException("exception.ProductNotFound", HttpStatus.FORBIDDEN);
        }
    }

    public boolean deleteProduct(Long id){
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if (productEntity != null){
            productRepository.delete(productEntity);
            return true;
        } else {
            throw new BaseException("exception.ProductNotFound",HttpStatus.NOT_FOUND);
        }
    }

    public ProductDto findById(Long id){
        ProductEntity product = productRepository.findById(id).orElse(null);
        if (product != null){
            return mapper.convertEntityToDTO(product, ProductDto.class);
        } else {
            throw new BaseException("exception.ProductNotFound=",HttpStatus.NOT_FOUND);
        }

    }

    public List<ProductDto> findByProductNameOrDescription(String name) {
        List<ProductDto> list = new ArrayList<>();
        try {
            String searchKey = "%".concat(name.concat("%")).replace(" ","%").toLowerCase();
            List<ProductEntity> products = productRepository.findAllByProductNameIsLikeIgnoreCaseOrDescriptionIsLikeIgnoreCase(searchKey, searchKey);
            if (!products.isEmpty()) {
                products.forEach(p -> {
                    ProductDto dto = mapper.convertEntityToDTO(p, ProductDto.class);
                    list.add(dto);
                });
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Page<ProductDto> findAll(Integer page, Integer size, String sortBy) {
        try {
            Sort sortType = pageableService.validateSortType(sortBy);
            PageableRequest pageableRequest = new PageableRequest(page, size, sortType);
            return productRepository.findAll(pageableRequest).map(pe -> mapper.convertEntityToDTO(pe, ProductDto.class));
        } catch (PageableDtoException pe) {
            throw new RuntimeException();
        }
    }
}
