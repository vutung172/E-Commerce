package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.exception.PageableDtoException;
import com.main.ra.model.dto.ProductDto;
import com.main.ra.model.dto.request.PageableRequest;
import com.main.ra.model.dto.request.ProductRequest;
import com.main.ra.model.entity.ProductEntity;
import com.main.ra.repository.ProductRepository;
import com.main.ra.service.BaseService;
import com.main.ra.service.ProductService;
import com.main.ra.util.FileServiceImpl;
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
public class ProductServiceImpl implements BaseService<ProductEntity,Long,ProductRequest>,ProductService {
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

    @Override
    public ProductEntity add(ProductRequest request) {
        ProductEntity product = productRepository.findProductEntitiesByProductName(request.getProductName());
        if (product == null) {
            product = mapper.convertDTOToEntity(request, ProductEntity.class);
            if (request.getImage().getSize() > 0){
                String fileLocation = fileUploadLocation.concat("/CategoryId_"+request.getCategoryId()+"/"+request.getProductName());
                String fileName = fileService.save(fileLocation,request.getImage());
                product.setImage(fileName);
            }
            return productRepository.save(product);
        } else {
            throw new BaseException("exception.ProductExisted", HttpStatus.FORBIDDEN);
        }
    }
    @Override
    public ProductEntity update(Long id, ProductRequest request){
        ProductEntity product = productRepository.findById(id).orElse(null);
        if (product != null){
           product = mapper.updateToEntity(request,product);
            if (request.getImage().getSize() > 0){
                String fileLocation = fileUploadLocation.concat("/CategoryId_"+request.getCategoryId()+"/"+request.getProductName());
                String fileName = fileService.save(fileLocation,request.getImage());
                product.setImage(fileName);
            }
            return productRepository.save(product);
        }else {
            throw new BaseException("exception.ProductNotFound", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(Long id){
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if (productEntity != null){
            productRepository.delete(productEntity);
            return true;
        } else {
            throw new BaseException("exception.ProductNotFound",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ProductEntity findById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductEntity> findAll(){
        return productRepository.findAll();
    };
    public Page<ProductDto> findAllPages(Integer page, Integer size, String sortBy) {
        try {
            Sort sortType = pageableService.validateSortType(sortBy);
            PageableRequest pageableRequest = new PageableRequest(page, size, sortType);
            return productRepository.findAll(pageableRequest).map(pe -> mapper.convertEntityToDTO(pe, ProductDto.class));
        } catch (PageableDtoException pe) {
            throw new RuntimeException();
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
}
