package com.api.bridge.dao.domain;

import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.utils.UserHelperUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * project
 *
 * @author
 */
public class Project {

    //状态|1-正常，0-删除
    public static final Integer NORMAL_STATUS=1;
    public static final Integer DELETE_STATUS=0;


    /**
     * 主键
     */
    private Long id;

    /**
     * 项目名
     */
    private String name;

    /**
     * 描述
     */
    private String description;
    /**
     * 状态|1-正常，0-删除
     */
    private Integer status = 1;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<PermissionPath> generatePermissionPath() {
        List<PermissionPath> res = new ArrayList<>();
        for (PermissionPathType value : PermissionPathType.values()) {
            PermissionPath permissionPath = new PermissionPath();
            permissionPath.setProjectId(this.id);
            permissionPath.setPathType(value.getType());
            UserHelperUtil.fillCreateInfo(permissionPath);
            UserHelperUtil.fillEditInfo(permissionPath);

            res.add(permissionPath);
        }
        return res;
    }
}