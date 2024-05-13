package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.exception.PageableDtoException;
import com.main.ra.model.dto.CategoryDto;
import com.main.ra.model.dto.request.CategoryRequest;
import com.main.ra.model.dto.request.PageableRequest;
import com.main.ra.model.entity.CategoryEntity;
import com.main.ra.model.entity.ProductEntity;
import com.main.ra.repository.CategoryRepository;
import com.main.ra.repository.ProductRepository;
import com.main.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PageableServiceImpl pageableService;
    @Autowired
    private MapperUtilServiceImpl mapper;

    public CategoryDto add(CategoryRequest request) {
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntitiesByCategoryName(request.getCategoryName()).orElse(null);
        if (categoryEntity == null){
            categoryEntity = mapper.convertDTOToEntity(request, CategoryEntity.class);
            CategoryEntity categoryDB = categoryRepository.save(categoryEntity);
            return mapper.convertEntityToDTO(categoryDB, CategoryDto.class);
        } else {
            throw new BaseException("exception.CategoryNameExisted",HttpStatus.BAD_REQUEST);
        }

    }

    public CategoryDto update(Long id, CategoryRequest request){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if (categoryEntity != null){
            if (categoryEntity.getStatus()){
                CategoryEntity category = categoryRepository.findCategoryEntitiesByCategoryName(request.getCategoryName()).orElse(null);
                if (category != null){
                    categoryEntity = mapper.updateToEntity(request,categoryEntity);
                    CategoryEntity categoryDB = categoryRepository.save(categoryEntity);
                    return mapper.convertEntityToDTO(categoryDB, CategoryDto.class);
                } else {
                    throw new BaseException("exception.CategoryNameExisted",HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new BaseException("exception.CategoryDisable",HttpStatus.FORBIDDEN);
            }
        }else {
            throw new BaseException("exception.CategoryNotFound", HttpStatus.NOT_FOUND);
        }
    }

    public boolean delete(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        List<ProductEntity> products = productRepository.findAllByCategoryId(id);
        if (categoryEntity!= null){
            if (products.isEmpty()){
                categoryEntity.setStatus(false);
                categoryRepository.save(categoryEntity);
                return categoryRepository.findById(categoryEntity.getId()).get().getStatus();
            } else {
                throw new BaseException("exception.CategoryHasProduct",HttpStatus.FORBIDDEN);
            }
        } else {
            throw new BaseException("exception.CategoryNotFound",HttpStatus.NOT_FOUND);
        }
    }

    public Page<CategoryDto> findAll(Integer page, Integer size, String sortBy){
        try {
            Sort sortType = pageableService.validateSortType(sortBy);
            PageableRequest pageableRequest = new PageableRequest(page, size, sortType);
            return categoryRepository.findAll(pageableRequest).map(ce -> mapper.convertEntityToDTO(ce,CategoryDto.class));
        } catch (PageableDtoException pe) {
            throw new RuntimeException();
        }
    }

    public CategoryDto findById(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if (categoryEntity  != null){
            return mapper.convertEntityToDTO(categoryEntity , CategoryDto.class);
        } else {
            throw new BaseException("exception.CategoryNotFound", HttpStatus.NOT_FOUND);
        }
    }
}
