package com.api.bridge.controller;

import com.api.bridge.constant.Status;
import com.api.bridge.dao.domain.UserPermission;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.dto.permission.PermissionPathTypeResDto;
import com.api.bridge.dto.permission.ProjectAutoInfoResDto;
import com.api.bridge.dto.permission.UserPermissionReqDto;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.service.AuthorizationService;
import com.api.bridge.service.UserPermissionService;
import com.api.bridge.utils.SecretUtil;
import com.api.bridge.utils.UserHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UserPermissionService userPermissionService;
    @GetMapping("/getPermissionTypeList")
    public ResultDto<List<PermissionPathTypeResDto>> getPermissionTypeList(){
        List<PermissionPathTypeResDto> res = new ArrayList<>();
        for (PermissionPathType value : PermissionPathType.values()) {
            res.add(new PermissionPathTypeResDto(value.getType(),value.getDesc()));
        }

        return ResultDto.createSuccess(res);
    }

    @PostMapping("/addPermission")
    public ResultDto<String> addPermission(@RequestBody @Validated({Insert.class}) UserPermissionReqDto userPermissionReqDto){
        authorizationService.validate(Long.parseLong(SecretUtil.decrypt(userPermissionReqDto.getProjectId())), PermissionPathType.PERMISSION_EDIT);
        userPermissionService.addPermission(userPermissionReqDto);
        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @PostMapping("/deletePermission")
    public ResultDto<String> deletePermission(@RequestBody @Validated({Delete.class}) UserPermissionReqDto userPermissionReqDto){
        authorizationService.validate(Long.parseLong(SecretUtil.decrypt(userPermissionReqDto.getProjectId())), PermissionPathType.PERMISSION_EDIT);
        userPermissionService.deletePermission(userPermissionReqDto);
        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @GetMapping("/getProjectAuthInfo")
    public ResultDto<List<ProjectAutoInfoResDto>> getProjectAuthInfo(String projectId){
        long pId = Long.parseLong(SecretUtil.decrypt(projectId));
        authorizationService.validate(pId, PermissionPathType.PERMISSION_EDIT);
        List<ProjectAutoInfoResDto> res = userPermissionService.getProjectAuthInfo(pId, UserHelperUtil.getUser().getId());
        return ResultDto.createSuccess(res);
    }
}
