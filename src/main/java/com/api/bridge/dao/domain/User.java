package com.api.bridge.dao.domain;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    //状态|0-未激活，1-激活，2-停用
    public static final Integer INACTIVATED_STATUS = 0;
    public static final Integer ACTIVATED_STATUS = 1;
    public static final Integer STOP_STATUS = 2;
    private Long id;
    private String name;
    private String email;

    private String password;

    private Integer status = INACTIVATED_STATUS;

    private Date createTime;

    private Date editTime;

    private String creator;

    private String editor;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
