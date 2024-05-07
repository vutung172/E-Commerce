package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.exception.PageableDtoException;
import com.main.ra.model.dto.CategoryDto;
import com.main.ra.model.dto.request.CategoryRequest;
import com.main.ra.model.dto.request.PageableRequest;
import com.main.ra.model.entity.CategoryEntity;
import com.main.ra.repository.CategoryRepository;
import com.main.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PageableServiceImpl pageableService;
    @Autowired
    private MapperUtilServiceImpl mapper;

    public CategoryDto add(CategoryRequest request) {
            CategoryEntity categoryEntity = mapper.convertDTOToEntity(request, CategoryEntity.class);
            CategoryEntity categoryDB = categoryRepository.save(categoryEntity);
            return mapper.convertEntityToDTO(categoryDB, CategoryDto.class);
    }

    public CategoryDto update(Long id, CategoryRequest request){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if (categoryEntity != null){
            CategoryEntity updatedCategory = mapper.updateToEntity(request,categoryEntity);
            CategoryEntity categoryDB = categoryRepository.save(updatedCategory);
            return mapper.convertEntityToDTO(categoryDB, CategoryDto.class);
        }else {
            throw new BaseException("exception.CategoryNotFound", HttpStatus.NOT_FOUND);
        }
    }

    public boolean delete(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if (categoryEntity!= null){
            categoryEntity.setStatus(false);
            categoryRepository.save(categoryEntity);
            return categoryRepository.findById(categoryEntity.getId()).get().getStatus();
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
