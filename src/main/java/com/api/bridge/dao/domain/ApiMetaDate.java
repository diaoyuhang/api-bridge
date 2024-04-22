package com.api.bridge.dao.domain;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * api_meta_date
 * @author 
 */
public class ApiMetaDate implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 组id|tag_group.id
     */
    private String tagId;

    /**
     * 接口请求路径
     */
    private String path;

    /**
     * 请求方法|get post put delete
     */
    private String method;

    /**
     * 接口名
     */
    private String summary;

    /**
     * api元数据
     */
    private String metaDate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String editor;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
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

    public String getMetaDate() {
        return metaDate;
    }

    public void setMetaDate(String metaDate) {
        this.metaDate = metaDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public ApiMetaDateHistory createHistory(Integer operationType) {
        ApiMetaDateHistory apiMetaDateHistory = new ApiMetaDateHistory();
        BeanUtils.copyProperties(this,apiMetaDateHistory);
        apiMetaDateHistory.setId(null);
        apiMetaDateHistory.setApiId(this.id);
        apiMetaDateHistory.setOperationType(operationType);
        return apiMetaDateHistory;
    }
}