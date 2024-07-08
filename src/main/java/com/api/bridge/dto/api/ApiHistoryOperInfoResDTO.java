package com.api.bridge.dto.api;

import com.api.bridge.dao.domain.ApiMetaDateHistory;
import com.api.bridge.utils.SecretUtil;

import java.util.Date;

public class ApiHistoryOperInfoResDTO {
    private String historyId;

    private String apiId;

    private Integer operationType;

    private String editor;

    private Date editTime;

    public static ApiHistoryOperInfoResDTO create(ApiMetaDateHistory history) {
        ApiHistoryOperInfoResDTO apiHistoryOperInfoResDTO = new ApiHistoryOperInfoResDTO();
        apiHistoryOperInfoResDTO.setHistoryId(SecretUtil.encrypt(history.getId().toString()));
        apiHistoryOperInfoResDTO.setApiId(SecretUtil.encrypt(history.getApiId().toString()));
        apiHistoryOperInfoResDTO.setOperationType(history.getOperationType());
        apiHistoryOperInfoResDTO.setEditor(history.getEditor());
        apiHistoryOperInfoResDTO.setEditTime(history.getEditTime());

        return apiHistoryOperInfoResDTO;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}
