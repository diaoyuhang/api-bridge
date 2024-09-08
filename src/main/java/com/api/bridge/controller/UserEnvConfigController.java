package com.api.bridge.controller;

import com.api.bridge.constant.Status;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.user.UserEnvConfigReqDto;
import com.api.bridge.dto.user.UserEnvConfigResDto;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.dto.validGroup.Update;
import com.api.bridge.service.UserEnvConfigService;
import com.api.bridge.utils.SecretUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userEnvConfig")
@Tag(name = "用户环境配置",description = "002cd7a7-8511-43b6-8bdb-c20cf2dabcaa")
public class UserEnvConfigController {

    @Autowired
    private UserEnvConfigService userEnvConfigService;

    @PostMapping("/add")
    @Operation(summary = "添加配置")
    public ResultDto<UserEnvConfigResDto> add(@RequestBody @Validated({Insert.class}) UserEnvConfigReqDto userEnvConfigReqDto){
        UserEnvConfigResDto userEnvConfigResDto =userEnvConfigService.addConfig(userEnvConfigReqDto);
        return ResultDto.createSuccess(userEnvConfigResDto);
    }

    @PostMapping("/update")
    @Operation(summary = "更新配置")
    public ResultDto<String> update(@RequestBody @Validated({Update.class}) UserEnvConfigReqDto userEnvConfigReqDto){
        userEnvConfigService.updateConfig(userEnvConfigReqDto);
        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @PostMapping("/delete")
    @Operation(summary = "删除配置")
    public ResultDto<String> delete(@RequestBody @Validated({Delete.class}) UserEnvConfigReqDto userEnvConfigReqDto){
        userEnvConfigService.deleteConfig(Long.valueOf(SecretUtil.decrypt(userEnvConfigReqDto.getId())));

        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @GetMapping("/get")
    @Operation(summary = "获取配置")
    public ResultDto<List<UserEnvConfigResDto>> get(){
        List<UserEnvConfigResDto> res = userEnvConfigService.getConfigs();
        return ResultDto.createSuccess(res);
    }
}
