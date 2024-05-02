package com.main.ra.model.dto.request;

import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayloadRequest<K,V> {
    private Map<K,V> payload;
}
