package com.api.bridge.dto.api;

import com.api.bridge.dao.domain.ApiMetaDate;
import com.api.bridge.dao.domain.TagGroup;
import com.api.bridge.utils.SecretUtil;
import com.api.bridge.utils.UserHelperUtil;
import jakarta.validation.constraints.NotBlank;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ApiMetaDateReqDto {
    @NotBlank(message = "tagId is empty")
    private String tagId;
    @NotBlank(message = "tagName is empty")
    private String tagName;
    @NotBlank(message = "projectId is empty")
    private String projectId;
    private List<String> apiMeteDateList;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTagName() {
        return tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public List<String> getApiMeteDateList() {
        return apiMeteDateList;
    }

    public void setApiMeteDateList(List<String> apiMeteDateList) {
        this.apiMeteDateList = apiMeteDateList;
    }

    public TagGroup createTagGroup() {
        try {
            TagGroup tagGroup = new TagGroup();
            tagGroup.setId(this.tagId);
            tagGroup.setName(this.tagName);
            tagGroup.setProjectId(Long.parseLong(SecretUtil.decrypt(this.projectId)));
            UserHelperUtil.fillCreateInfo(tagGroup);
            UserHelperUtil.fillEditInfo(tagGroup);
            return tagGroup;
        } catch (Exception e) {
            throw new RuntimeException("tag组装失败"+e.getMessage());
        }
    }

    public List<ApiMetaDate> createApiMetaDateList() {
        ArrayList<ApiMetaDate> res = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.apiMeteDateList)){
            for (String api : this.apiMeteDateList) {
                ApiMetaDate apiMetaDate = new ApiMetaDate();

                res.add(apiMetaDate);
            }
        }
        return res;
    }
}
