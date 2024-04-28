package com.main.ra.config;


import com.main.ra.util.ListLoader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiControlConfig {
    @Autowired
    private ListLoader loader;
    private String[] WHITE_LIST_API;
    private String[] ADMIN_LIST_API;
    private String[] USER_LIST_API;

    public ApiControlConfig() {
        loader = new ListLoader();
        this.WHITE_LIST_API = loader.getList("permission-list.permit-all");
        this.ADMIN_LIST_API = loader.getList("permission-list.admin");
        this.USER_LIST_API = loader.getList("permission-list.user");
    }
}
