package com.api.bridge.service.impl;

import com.api.bridge.dao.ProjectRequestParamDao;
import com.api.bridge.dao.TagGroupDao;
import com.api.bridge.dao.domain.ProjectRequestParam;
import com.api.bridge.dao.domain.TagGroup;
import com.api.bridge.dto.ProjectRequestParam.ProjectRequestParamReqDto;
import com.api.bridge.dto.ProjectRequestParam.ProjectRequestParamResDto;
import com.api.bridge.service.ProjectRequestParamService;
import com.api.bridge.utils.SecretUtil;
import com.api.bridge.utils.UserHelperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectRequestParamServiceImpl implements ProjectRequestParamService {

    private final static Logger logger = LoggerFactory.getLogger(ProjectRequestParamServiceImpl.class);

    @Autowired
    private ProjectRequestParamDao projectRequestParamDao;
    @Autowired
    private TagGroupDao tagGroupDao;

    @Override
    public List<ProjectRequestParamResDto> getParam(Long projectId) {
        Assert.notNull(projectId,"projectId is null");
        List<ProjectRequestParam> paramList = projectRequestParamDao.selectByProjectId(projectId);

        return paramList.stream().map(ProjectRequestParamResDto::create).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addParam(ProjectRequestParamReqDto projectRequestParamReqDto) {
        ProjectRequestParam projectRequestParam = projectRequestParamReqDto.convertProjectRequestParam();

        UserHelperUtil.fillCreateInfo(projectRequestParam);
        UserHelperUtil.fillEditInfo(projectRequestParam);
        projectRequestParamDao.insert(projectRequestParam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateParam(ProjectRequestParamReqDto projectRequestParamReqDto) {
        ProjectRequestParam projectRequestParam = projectRequestParamReqDto.convertProjectRequestParam();
        ProjectRequestParam oldPrp = projectRequestParamDao.selectByPrimaryKey(projectRequestParam.getId());

        Assert.notNull(oldPrp,"不存在对应的记录");
        oldPrp.setType(projectRequestParam.getType());
        oldPrp.setName(projectRequestParam.getName());
        oldPrp.setRequired(projectRequestParam.getRequired());
        UserHelperUtil.fillEditInfo(oldPrp);

        projectRequestParamDao.updateByPrimaryKey(oldPrp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteParam(ProjectRequestParamReqDto projectRequestParamReqDto) {
        Long id = Long.valueOf(SecretUtil.decrypt(projectRequestParamReqDto.getId()));
        Long projectId = Long.valueOf(SecretUtil.decrypt(projectRequestParamReqDto.getProjectId()));
        ProjectRequestParam projectRequestParam = projectRequestParamDao.selectByIdAndProjectId(id,projectId);
        Assert.notNull(projectRequestParam,"删除记录不存在该项目下");

        projectRequestParamDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ProjectRequestParam> getParamByTagId(String tagId) {
        TagGroup tagGroup = tagGroupDao.selectByPrimaryKey(tagId);
        Assert.notNull(tagGroup,tagId+" 不存在");

        return projectRequestParamDao.selectByProjectId(tagGroup.getProjectId());
    }
}
