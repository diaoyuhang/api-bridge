package com.api.bridge.service.impl;

import com.api.bridge.dao.ApiMetaDateDao;
import com.api.bridge.dao.ApiMetaDateHistoryDao;
import com.api.bridge.dao.ProjectDao;
import com.api.bridge.dao.TagGroupDao;
import com.api.bridge.dao.domain.ApiMetaDate;
import com.api.bridge.dao.domain.ApiMetaDateHistory;
import com.api.bridge.dao.domain.TagGroup;
import com.api.bridge.dto.api.*;
import com.api.bridge.service.ApiMetaDateService;
import com.api.bridge.utils.SecretUtil;
import com.api.bridge.utils.UserHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiMetaDateServiceImpl implements ApiMetaDateService {

    @Autowired
    private TagGroupDao tagGroupDao;
    @Autowired
    private ApiMetaDateDao apiMetaDateDao;

    @Autowired
    private ApiMetaDateHistoryDao apiMetaDateHistoryDao;

    @Autowired
    private ProjectDao projectDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addApiMetaDate(ApiMetaDateReqDto apiMetaDateReqDto) {
        Long projectId = Long.parseLong(SecretUtil.decrypt(apiMetaDateReqDto.getProjectId()));
        Assert.isTrue(projectDao.selectCountByPrimaryKey(projectId) > 0, String.format("%s 不存在", apiMetaDateReqDto.getProjectId()));

        TagGroup tagGroup = apiMetaDateReqDto.createTagGroup();
        TagGroup oldTagGroup = tagGroupDao.selectByPrimaryKey(tagGroup.getId());
        if (oldTagGroup == null) {
            tagGroupDao.insert(tagGroup);
        } else {
            oldTagGroup.setName(tagGroup.getName());
            UserHelperUtil.fillEditInfo(oldTagGroup);
            tagGroupDao.updateByPrimaryKey(oldTagGroup);
        }

        List<ApiMetaDate> apiMetaDateList = apiMetaDateReqDto.createApiMetaDateList();
        for (ApiMetaDate apiMetaDate : apiMetaDateList) {
            ApiMetaDate oldApiMetaDate = apiMetaDateDao.selectByTagIdAndPathAndMethod(apiMetaDate);
            if (oldApiMetaDate == null) {
                apiMetaDateDao.insert(apiMetaDate);
                apiMetaDateHistoryDao.insert(apiMetaDate.createHistory(ApiMetaDateHistory.ADD_OPERATION_TYPE));
            } else {
                oldApiMetaDate.setSummary(apiMetaDate.getSummary());
                oldApiMetaDate.setMetaDate(apiMetaDate.getMetaDate());
                UserHelperUtil.fillEditInfo(oldApiMetaDate);
                apiMetaDateDao.updateByPrimaryKey(oldApiMetaDate);
                apiMetaDateHistoryDao.insert(oldApiMetaDate.createHistory(ApiMetaDateHistory.UPDATE_OPERATION_TYPE));
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteApiMetaDate(Long apiId) {
        ApiMetaDate apiMetaDate = apiMetaDateDao.selectByPrimaryKey(apiId);
        Assert.notNull(apiMetaDate,"未查询到对应的api信息");
        apiMetaDateDao.deleteByPrimaryKey(apiId);
    }

    @Override
    public Long getProjectIdByApiId(Long apiId) {
        return apiMetaDateDao.selectProjectIdByApiId(apiId);
    }

    @Override
    public List<PathInfoResDto> getPathInfo(String tagId) {
        List<ApiMetaDate> apiMetaDateList = apiMetaDateDao.selectPathInfoByTagId(tagId);

        return apiMetaDateList.stream().map(a->PathInfoResDto.create(a)).collect(Collectors.toList());
    }

    @Override
    public ApiMetaDate getMetaDateInfo(Long apiId) {
        ApiMetaDate apiMetaDate = apiMetaDateDao.selectByPrimaryKey(apiId);
        Assert.notNull(apiMetaDate,"未查询到api元数据");
        return apiMetaDate;
    }

    @Override
    public OpenApiBasicInfoResDto getBasicApiInfoList(Long projectId) {
        OpenApiBasicInfoResDto openApiBasicInfoResDto = new OpenApiBasicInfoResDto();

        List<TagGroup> tagGroupList = tagGroupDao.selectByProjectId(projectId);
        List<Tag> tags = tagGroupList.stream().map(tag -> new Tag(tag.getName(), tag.getId())).collect(Collectors.toList());
        openApiBasicInfoResDto.setTags(tags);

        Map<String, Map<String,ApiBasicInfoGroup>> paths = new HashMap<>();
        openApiBasicInfoResDto.setPaths(paths);

        Map<String, String> tagIdMapName = new HashMap<>();
        for (TagGroup tagGroup : tagGroupList) {
            tagIdMapName.put(tagGroup.getId(),tagGroup.getName());
        }

        List<ApiMetaDate> apiMetaDateList = apiMetaDateDao.selectBasicInfoByTagIds(tagIdMapName.keySet());
        for (ApiMetaDate apiMetaDate : apiMetaDateList) {
            Map<String,ApiBasicInfoGroup> m = new HashMap<>();

            ApiBasicInfoGroup apiBasicInfoGroup = new ApiBasicInfoGroup(Arrays.asList(tagIdMapName.get(apiMetaDate.getTagId())),
                    SecretUtil.encrypt(apiMetaDate.getId().toString()),
                    apiMetaDate.getSummary());

            if (paths.containsKey(apiMetaDate.getPath())){
                m = paths.get(apiMetaDate.getPath());
            }else{
                paths.put(apiMetaDate.getPath(),m);
            }
            m.put(apiMetaDate.getMethod(),apiBasicInfoGroup);
        }

        return openApiBasicInfoResDto;
    }

    @Override
    public List<ApiHistoryOperInfoResDTO> getApiHistoryInfo(Long apiId) {
        List<ApiHistoryOperInfoResDTO> resDto = new ArrayList<>();
        List<ApiMetaDateHistory> histories = apiMetaDateHistoryDao.selectByApiIdDescEditTime(apiId);
        for (ApiMetaDateHistory history : histories) {
            resDto.add(ApiHistoryOperInfoResDTO.create(history));
        }
        return resDto;
    }
}
