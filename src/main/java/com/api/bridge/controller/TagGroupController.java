package com.api.bridge.controller;

import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.dto.tag.TagGroupResDto;
import com.api.bridge.service.AuthorizationService;
import com.api.bridge.service.TagGroupService;
import com.api.bridge.utils.SecretUtil;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@Validated
public class TagGroupController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private TagGroupService tagGroupService;
    @GetMapping("/getTagGroupList")
    public ResultDto<TagGroupResDto> getTagGroupList(@NotBlank(message = "projectId is empty") String projectId){
        authorizationService.validate(Long.parseLong(SecretUtil.decrypt(projectId)), PermissionPathType.PROJECT_EDIT);
        return ResultDto.createSuccess(null);
    }
}
