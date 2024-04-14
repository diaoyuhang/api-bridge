package com.api.bridge.dto.api;

import java.util.List;

public class ApiMetaDateReqDto {
    private String uuid;
    private List<String> apiMeteDateList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getApiMeteDateList() {
        return apiMeteDateList;
    }

    public void setApiMeteDateList(List<String> apiMeteDateList) {
        this.apiMeteDateList = apiMeteDateList;
    }
}
