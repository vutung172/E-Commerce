package com.main.ra.config;


import com.main.ra.util.ListLoader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiControlConfig {
    @Autowired
    private ListLoader loader;
    private final String[] WHITE_LIST_API;
    private final String[] ADMIN_LIST_API;
    private final String[] USER_LIST_API;

    public ApiControlConfig() {
        loader = new ListLoader();
        this.WHITE_LIST_API = loader.getList("permission-list.permit-all");
        this.ADMIN_LIST_API = loader.getList("permission-list.admin");
        this.USER_LIST_API = loader.getList("permission-list.user");
    }
}
