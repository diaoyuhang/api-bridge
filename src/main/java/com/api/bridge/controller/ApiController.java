package com.api.bridge.controller;

import com.api.bridge.constant.Status;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.api.ApiMetaDateReqDto;
import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.service.ApiMetaDateService;
import com.api.bridge.service.AuthorizationService;
import com.api.bridge.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiMetaDateService apiMetaDateService;
    @Autowired
    private AuthorizationService authorizationService;
    @PostMapping("/acceptApiMetaDate")
    public ResultDto<String> acceptApiMetaDate(@RequestBody @Validated({Insert.class}) ApiMetaDateReqDto apiMetaDateReqDto) {
        authorizationService.validate(Long.parseLong(SecretUtil.decrypt(apiMetaDateReqDto.getProjectId())), PermissionPathType.API_UPLOAD);
        apiMetaDateService.addApiMetaDate(apiMetaDateReqDto);
        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @PostMapping("/deleteApiMetaDate")
    public ResultDto<String> deleteApiMetaDate(@RequestBody @Validated({Delete.class}) ApiMetaDateReqDto apiMetaDateReqDto){
        Long apiId = Long.parseLong(SecretUtil.decrypt(apiMetaDateReqDto.getApiId()));
        Long projectId = apiMetaDateService.getProjectIdByApiId(apiId);
        Assert.notNull(projectId,"未查询到归属的项目");

        authorizationService.validate(projectId, PermissionPathType.API_DELETE);
        apiMetaDateService.deleteApiMetaDate(apiId);
        return ResultDto.createSuccess(Status.ok.getMessage());
    }
}
