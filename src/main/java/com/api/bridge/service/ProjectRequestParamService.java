package com.api.bridge.service;

import com.api.bridge.dto.ProjectRequestParam.ProjectRequestParamReqDto;
import com.api.bridge.dto.ProjectRequestParam.ProjectRequestParamResDto;

import java.util.List;

public interface ProjectRequestParamService {
    List<ProjectRequestParamResDto> getParam(Long projectId);

    void addParam(ProjectRequestParamReqDto projectRequestParamReqDto);

    void updateParam(ProjectRequestParamReqDto projectRequestParamReqDto);

    void deleteParam(ProjectRequestParamReqDto projectRequestParamReqDto);
}
