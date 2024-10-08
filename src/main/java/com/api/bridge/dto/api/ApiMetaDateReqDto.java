package com.api.bridge.dto.api;

import com.api.bridge.dao.ProjectDao;
import com.api.bridge.dao.domain.ApiMetaDate;
import com.api.bridge.dao.domain.Project;
import com.api.bridge.dao.domain.TagGroup;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.service.TagGroupService;
import com.api.bridge.utils.OpenApiUtil;
import com.api.bridge.utils.SecretUtil;
import com.api.bridge.utils.UserHelperUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiMetaDateReqDto {
    public static final String GET_METHOD = "get";
    public static final String DELETE_METHOD = "delete";
    public static final String PUT_METHOD = "put";
    public static final String POST_METHOD = "post";
    @NotBlank(message = "apiId is empty",groups = {Delete.class})
    private String apiId;
    @NotBlank(message = "tagId is empty",groups = {Insert.class})
    private String tagId;
    @NotBlank(message = "tagName is empty",groups = {Insert.class})
    private String tagName;
    @NotBlank(message = "projectId is empty",groups = {Insert.class})
    private String projectId;
    private List<String> apiMeteDateList;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

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

    public List<ApiMetaDate> createApiMetaDateList(ProjectDao projectDao) {
        ArrayList<ApiMetaDate> res = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.apiMeteDateList)) {
            Project project = projectDao.selectByPrimaryKey(Long.parseLong(SecretUtil.decrypt(this.projectId)));

            for (String api : this.apiMeteDateList) {
                ApiMetaDate apiMetaDate = new ApiMetaDate();

                OpenAPI openAPI = OpenApiUtil.readJson(api, OpenAPI.class);
                Assert.isTrue(!CollectionUtils.isEmpty(openAPI.getTags()), "tag 信息为空");
                Assert.isTrue(StringUtils.isNotEmpty(openAPI.getTags().get(0).getDescription()), "tag description为空");


                openAPI.getInfo().setTitle(project.getName());
                Paths paths = openAPI.getPaths();
                Assert.isTrue(!CollectionUtils.isEmpty(paths), "path信息为空");
                Assert.isTrue(paths.entrySet().size() == 1, "paths长度大于1");
                for (Map.Entry<String, PathItem> entry : paths.entrySet()) {
                    String path = entry.getKey();
                    PathItem value = entry.getValue();

                    if (value.getGet() != null) {
                        apiMetaDate.setMethod(GET_METHOD);
                        apiMetaDate.setSummary(value.getGet().getSummary());
                    } else if (value.getPost() != null) {
                        apiMetaDate.setMethod(POST_METHOD);
                        apiMetaDate.setSummary(value.getPost().getSummary());
                    } else if (value.getPut() != null) {
                        apiMetaDate.setMethod(PUT_METHOD);
                        apiMetaDate.setSummary(value.getPut().getSummary());
                    } else if (value.getDelete() != null) {
                        apiMetaDate.setMethod(DELETE_METHOD);
                        apiMetaDate.setSummary(value.getDelete().getSummary());
                    }
                    apiMetaDate.setPath(path);
                }
                UserHelperUtil.fillCreateInfo(apiMetaDate);
                UserHelperUtil.fillEditInfo(apiMetaDate);

                apiMetaDate.setMetaDate(OpenApiUtil.writeJson(openAPI));
                apiMetaDate.setTagId(tagId);
                res.add(apiMetaDate);
            }
        }
        return res;
    }
}
