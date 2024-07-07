package com.api.bridge.dto.api;

import java.util.List;
import java.util.Map;

public class OpenApiBasicInfoResDto {

    private String openapi="3.0.1";

    private Info info;

    private List<Tag> tags;


    private Map<String, Map<String,ApiBasicInfoGroup>> paths;


    public String getOpenapi() {
        return openapi;
    }

    public void setOpenapi(String openapi) {
        this.openapi = openapi;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Map<String, Map<String, ApiBasicInfoGroup>> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Map<String, ApiBasicInfoGroup>> paths) {
        this.paths = paths;
    }
}
