package com.main.ra.model.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse<T> {
    private List<T> products = new ArrayList<>();
}
