package com.api.bridge.dto.api;

import com.api.bridge.dao.domain.ApiMetaDate;
import com.api.bridge.utils.SecretUtil;

public class PathInfoResDto {
    private String apiId;

    private String path;

    private String method;

    private String summary;

    public static PathInfoResDto create(ApiMetaDate apiMetaDate) {
        PathInfoResDto pathInfoResDto = new PathInfoResDto();
        pathInfoResDto.setApiId(SecretUtil.encrypt(apiMetaDate.getId().toString()));
        pathInfoResDto.setPath(apiMetaDate.getPath());
        pathInfoResDto.setMethod(pathInfoResDto.getMethod());
        pathInfoResDto.setSummary(apiMetaDate.getSummary());

        return pathInfoResDto;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
