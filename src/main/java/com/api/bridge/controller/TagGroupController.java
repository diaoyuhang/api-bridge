package com.api.bridge.controller;

import com.api.bridge.dao.domain.ApiMetaDate;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.dto.tag.TagGroupResDto;
import com.api.bridge.service.AuthorizationService;
import com.api.bridge.service.TagGroupService;
import com.api.bridge.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/tag")
@Validated
public class TagGroupController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private TagGroupService tagGroupService;
    @GetMapping("/getTagGroupList")
    public ResultDto<List<TagGroupResDto>> getTagGroupList(@NotBlank(message = "projectId is empty") String projectId){
        Long pId = Long.parseLong(SecretUtil.decrypt(projectId));
        authorizationService.validate(pId, PermissionPathType.PROJECT_VIEW);
        List<TagGroupResDto> res = tagGroupService.getTagGroupList(pId);
        return ResultDto.createSuccess(res);
    }

}
