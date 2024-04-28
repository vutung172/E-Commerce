package com.main.ra.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ListLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListLoader.class);
    private static Map<String, String[]> list;

    public ListLoader() {
        try {
            Properties properties = new Properties();
            list = new HashMap<>();
            properties.load(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/api-list.properties")), StandardCharsets.UTF_8));
            list = properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            l -> l.getKey().toString(),
                            l -> l.getValue().toString().split(",")
                    ));
        } catch (Exception ex) {
            LOGGER.error("ErrorMessageLoader load message failed with ex: {}", ex.getMessage());
        }
    }
    public String[] getList(String code) {
        return list.get(code);
    }
}
