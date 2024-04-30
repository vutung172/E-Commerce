package com.main.ra.model.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> {
    private List<T> data;
}
