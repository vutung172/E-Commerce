package com.main.ra.model.dto.request;

import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Builder
@Getter
@Setter
@NoArgsConstructor
public class PageableRequest implements Pageable{
    private Integer page;
    private Integer pageSize;
    private Sort sortType;

    public PageableRequest(Integer page, Integer pageSize, Sort sortType) {
        this.page = validateRange(page);
        this.pageSize = pageSize;
        this.sortType = sortType;
    }

    public Integer validateRange(Integer number){
        if (number < 1)
            return 1;
        return number;
    }

    @Override
    public int getPageNumber() {
        return this.page;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public long getOffset() {
        return (long) (this.page - 1) * this.pageSize;
    }


    @Override
    public Sort getSort() {
        return this.sortType;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
