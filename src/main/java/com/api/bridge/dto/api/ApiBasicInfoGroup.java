package com.api.bridge.dto.api;

import java.util.List;

public class ApiBasicInfoGroup {
    private List<String> tags;

    private String operationId;

    private String summary;

    public ApiBasicInfoGroup() {
    }

    public ApiBasicInfoGroup(List<String> tags, String operationId, String summary) {
        this.tags = tags;
        this.operationId = operationId;
        this.summary = summary;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
