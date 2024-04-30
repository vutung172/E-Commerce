package com.main.ra.model.dto.response;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class PageDataResponse<T> {
    private List<T> data;
    private Integer totalPages;
    private Integer number;
    private Integer size;
    private String sort;

    public PageDataResponse(List<T> data, Integer totalPages, Integer number, Integer size, String sort) {
        this.data = data;
        this.totalPages = totalPages;
        this.number = validateRange(number);
        this.size = size;
        this.sort = sort;
    }

    public Integer validateRange(Integer number){
        if (number < 1)
            return 1;
        if (number > this.totalPages)
            return this.totalPages;
        return number;
    }
}
