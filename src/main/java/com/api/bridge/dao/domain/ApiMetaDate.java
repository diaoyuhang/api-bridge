package com.api.bridge.dao.domain;

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
}