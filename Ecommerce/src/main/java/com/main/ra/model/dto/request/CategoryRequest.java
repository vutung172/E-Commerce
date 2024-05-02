package com.main.ra.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @Size(max = 100,message = "{message.Max-Length-100}")
    @NotNull(message = "{message.NotNull}")
    private String categoryName;

    private String description;
}
