package com.main.ra.service.Impl;

import com.main.ra.exception.PageableDtoException;
import com.main.ra.model.dto.ProductDto;
import com.main.ra.model.dto.request.PageableRequest;
import com.main.ra.model.entity.ProductEntity;
import com.main.ra.repository.ProductRepository;
import com.main.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MapperUtilServiceImpl mapper;
    @Autowired
    private PageableServiceImpl pageableService;


    public List<ProductDto> findByProductNameOrDescription(String name){
        List<ProductDto> list =  new ArrayList<>();
        try{
            List<ProductEntity> products = productRepository.findAllByProductNameOrDescription(name,name);
            if (!products.isEmpty()){
                products.forEach(p -> {
                    ProductDto dto = mapper.convertEntityToDTO(p, ProductDto.class);
                    list.add(dto);
                });
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Page<ProductDto> findAll(Integer page,Integer size, String sortBy){
        try{
            Sort sortType = pageableService.validateSortType(sortBy);
            PageableRequest pageableRequest = new PageableRequest(page,size,sortType);
            return productRepository.findAll(pageableRequest).map(pe -> mapper.convertEntityToDTO(pe, ProductDto.class));
        }catch (PageableDtoException pe){
            throw new RuntimeException();
        }
    }





}
