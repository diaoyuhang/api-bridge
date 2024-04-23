package com.api.bridge.dao.domain;

import com.api.bridge.utils.UserHelperUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户权限
 * user_permission
 */
public class UserPermission  {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id|user.id
     */
    private Long userId;

    /**
     * 权限id|permission_path.id
     */
    private Long permissionId;

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

    public static UserPermission create(Long userId,Long permissionId){
        UserPermission userPermission = new UserPermission();
        userPermission.setUserId(userId);
        userPermission.setPermissionId(permissionId);

        UserHelperUtil.fillCreateInfo(userPermission);
        UserHelperUtil.fillEditInfo(userPermission);

        return userPermission;
    }
    public static List<UserPermission> create(List<Long> permissionIds) {
        List<UserPermission> res = new ArrayList<>();
        User user = UserHelperUtil.getUser();
        for (Long permissionId : permissionIds) {
            UserPermission userPermission = new UserPermission();
            userPermission.setPermissionId(permissionId);
            userPermission.setUserId(user.getId());

            UserHelperUtil.fillCreateInfo(userPermission);
            UserHelperUtil.fillEditInfo(userPermission);
            res.add(userPermission);
        }
        return res;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
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