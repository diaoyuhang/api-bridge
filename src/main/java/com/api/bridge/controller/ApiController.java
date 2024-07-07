package com.api.bridge.controller;

import com.api.bridge.constant.Status;
import com.api.bridge.dao.domain.ApiMetaDate;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.api.ApiMetaDateReqDto;
import com.api.bridge.dto.api.OpenApiBasicInfoResDto;
import com.api.bridge.dto.api.PathInfoResDto;
import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.service.ApiMetaDateService;
import com.api.bridge.service.AuthorizationService;
import com.api.bridge.utils.SecretUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name="api元数据接口",description = "38bfea5c-310f-4068-86e9-5182bd855994")
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

    @GetMapping("/pathInfo/{tagId}")
    public ResultDto<List<PathInfoResDto>> pathInfo(@PathVariable(value = "tagId") String tagId){
        authorizationService.validateByTagId(tagId, PermissionPathType.PROJECT_VIEW);
        List<PathInfoResDto> res = apiMetaDateService.getPathInfo(tagId);
        return ResultDto.createSuccess(res);
    }

    @GetMapping("/apiMetaDateInfo")
    public ResultDto<String> apiMetaDateInfo(@NotBlank(message = "apiId is empty")  String apiId){
        ApiMetaDate apiMetaDate = apiMetaDateService.getMetaDateInfo(Long.parseLong(SecretUtil.decrypt(apiId)));
        return ResultDto.createSuccess(apiMetaDate.getMetaDate());
    }

    @GetMapping("/getBasicApiInfoList")
    public ResultDto<OpenApiBasicInfoResDto> getBasicApiInfoList(@NotBlank(message = "projectId is empty") String projectId){
        Long pId = Long.parseLong(SecretUtil.decrypt(projectId));
        authorizationService.validate(pId, PermissionPathType.PROJECT_VIEW);
        OpenApiBasicInfoResDto res = apiMetaDateService.getBasicApiInfoList(pId);
        return ResultDto.createSuccess(res);
    }
}
