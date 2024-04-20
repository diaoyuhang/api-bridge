package com.api.bridge.dto.api;

import com.api.bridge.dao.domain.ApiMetaDate;
import com.api.bridge.dao.domain.TagGroup;
import com.api.bridge.utils.OpenApiUtil;
import com.api.bridge.utils.SecretUtil;
import com.api.bridge.utils.UserHelperUtil;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import jakarta.validation.constraints.NotBlank;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            throw new RuntimeException("tag组装失败" + e.getMessage());
        }
    }

    public List<ApiMetaDate> createApiMetaDateList() {
        ArrayList<ApiMetaDate> res = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.apiMeteDateList)) {
            for (String api : this.apiMeteDateList) {
                ApiMetaDate apiMetaDate = new ApiMetaDate();
                apiMetaDate.setMetaDate(api);

                OpenAPI openAPI = OpenApiUtil.readJson(api, OpenAPI.class);
                Assert.isTrue(!CollectionUtils.isEmpty(openAPI.getTags()), "tag 信息为空");
                Assert.isTrue(StringUtils.isNotEmpty(openAPI.getTags().get(0).getDescription()), "tag description为空");

                Paths paths = openAPI.getPaths();
                Assert.isTrue(CollectionUtils.isEmpty(paths), "path信息为空");
                Assert.isTrue(paths.entrySet().size() > 1, "paths长度大于1");
                for (Map.Entry<String, PathItem> entry : paths.entrySet()) {
                    String path = entry.getKey();
                    PathItem value = entry.getValue();

                    if (value.getGet() != null) {
                        apiMetaDate.setMethod("get");
                    } else if (value.getPost() != null) {
                        apiMetaDate.setMethod("post");
                    } else if (value.getPut() != null) {
                        apiMetaDate.setMethod("put");
                    } else if (value.getDelete() != null) {
                        apiMetaDate.setMethod("delete");
                    }
                    apiMetaDate.setPath(path);
                }
                UserHelperUtil.fillCreateInfo(apiMetaDate);
                UserHelperUtil.fillEditInfo(apiMetaDate);

                apiMetaDate.setTagId(tagId);
                res.add(apiMetaDate);
            }
        }
        return res;
    }
}
