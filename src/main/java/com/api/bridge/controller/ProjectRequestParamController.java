package com.api.bridge.controller;

import com.api.bridge.constant.Status;
import com.api.bridge.dto.ProjectRequestParam.ProjectRequestParamReqDto;
import com.api.bridge.dto.ProjectRequestParam.ProjectRequestParamResDto;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.dto.validGroup.Update;
import com.api.bridge.service.AuthorizationService;
import com.api.bridge.service.ProjectRequestParamService;
import com.api.bridge.utils.SecretUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projectRequestParam")
public class ProjectRequestParamController {
    private final static Logger logger = LoggerFactory.getLogger(ProjectRequestParamController.class);
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private ProjectRequestParamService projectRequestParamService;

    @GetMapping("/getParam")
    public ResultDto<List<ProjectRequestParamResDto>> getParam(String projectId){
        String pId = SecretUtil.decrypt(projectId);
        List<ProjectRequestParamResDto>  res = projectRequestParamService.getParam(Long.valueOf(pId));

        return ResultDto.createSuccess(res);
    }

    @PostMapping("/addParam")
    public ResultDto<String> addParam(@RequestBody @Validated({Insert.class}) ProjectRequestParamReqDto projectRequestParamReqDto){
        String pId = SecretUtil.decrypt(projectRequestParamReqDto.getProjectId());

        authorizationService.validate(Long.valueOf(pId), PermissionPathType.PROJECT_EDIT);
        projectRequestParamService.addParam(projectRequestParamReqDto);

        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @PostMapping("/updateParam")
    public ResultDto<String> updateParam(@RequestBody @Validated({Update.class}) ProjectRequestParamReqDto projectRequestParamReqDto){
        String pId = SecretUtil.decrypt(projectRequestParamReqDto.getProjectId());

        authorizationService.validate(Long.valueOf(pId), PermissionPathType.PROJECT_EDIT);
        projectRequestParamService.updateParam(projectRequestParamReqDto);

        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @PostMapping("/deleteParam")
    public ResultDto<String> deleteParam(@RequestBody @Validated({Delete.class}) ProjectRequestParamReqDto projectRequestParamReqDto){
        String pId = SecretUtil.decrypt(projectRequestParamReqDto.getProjectId());

        authorizationService.validate(Long.valueOf(pId), PermissionPathType.PROJECT_EDIT);
        projectRequestParamService.deleteParam(projectRequestParamReqDto);

        return ResultDto.createSuccess(Status.ok.getMessage());
    }
}
