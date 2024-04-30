package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.exception.PageableDtoException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class PageableServiceImpl {
    public static Map<String, String> stringToMap(String inputString) {
        String[] parts = Arrays.stream(inputString.split(","))
                .map(String::trim)
                .toArray(String[]::new);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("sortField", parts[0]);
        resultMap.put("sortDirection", parts[1]);

        return resultMap;
    }
    public Sort validateSortType(String sort){
        try {
            Map<String,String> sortMap = stringToMap(sort);
            String sortDirection = sortMap.get("sortDirection");
            String sortFields = sortMap.get("sortField");
            if (sortDirection.equals("desc")){
                return Sort.by(Sort.Direction.DESC, sortFields);
            } else if (sortDirection.equals("asc")) {
                return Sort.by(Sort.Direction.ASC, sortFields);
            }
            return null;
        } catch (Exception exception){
            throw new BaseException("exception.pageable.SortTypeError",HttpStatus.NOT_FOUND);
        }

    }
}
