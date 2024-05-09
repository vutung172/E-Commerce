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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Value("${upload.Product.location}")
    private String fileUploadLocation;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private MapperUtilServiceImpl mapper;
    @Autowired
    private PageableServiceImpl pageableService;


    public ProductDto addProduct(ProductRequest request) {
        String fileLocation = fileUploadLocation.concat("/CategoryId_"+request.getCategoryId()+"/"+request.getProductName());
        if (productRepository.findProductEntitiesByProductName(request.getProductName()) == null) {
            String fileName = fileService.save(fileLocation,request.getImage());
            ProductEntity product = mapper.convertDTOToEntity(request, ProductEntity.class);
            product.setImage(fileName);
            ProductEntity productDB = productRepository.save(product);
            System.out.println(productDB.getSku());
            return mapper.convertEntityToDTO(productDB, ProductDto.class);
        } else {
            throw new BaseException("exception.ProductExisted", HttpStatus.FORBIDDEN);
        }
    }
    public ProductDto updateProduct(Long id, ProductRequest request){
        String fileLocation = fileUploadLocation.concat("/CategoryId_"+request.getCategoryId()+"/"+request.getProductName());
        ProductEntity product = productRepository.findById(id).orElse(null);
        if (product != null){
            String fileName = fileService.save(fileLocation,request.getImage());
            ProductEntity updatedProduct = mapper.updateToEntity(request,product);
            updatedProduct.setImage(fileName);
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
