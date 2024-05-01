package com.main.ra.model.dto.response;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageProductResponse<T> {
    private List<T> products = new ArrayList<>();
    private Integer totalPages;
    private Integer number;
    private Integer size;
    private String sort;
}
