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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userEnvConfig")
public class UserEnvConfigController {

    @Autowired
    private UserEnvConfigService userEnvConfigService;

    @PostMapping("/add")
    public ResultDto<UserEnvConfigResDto> add(@RequestBody @Validated({Insert.class}) UserEnvConfigReqDto userEnvConfigReqDto){
        UserEnvConfigResDto userEnvConfigResDto =userEnvConfigService.addConfig(userEnvConfigReqDto);
        return ResultDto.createSuccess(userEnvConfigResDto);
    }

    @PostMapping("/update")
    public ResultDto<String> update(@RequestBody @Validated({Update.class}) UserEnvConfigReqDto userEnvConfigReqDto){
        userEnvConfigService.updateConfig(userEnvConfigReqDto);
        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @DeleteMapping("/delete")
    public ResultDto<String> delete(@RequestBody @Validated({Delete.class}) UserEnvConfigReqDto userEnvConfigReqDto){
        userEnvConfigService.deleteConfig(Long.valueOf(SecretUtil.decrypt(userEnvConfigReqDto.getId())));

        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @GetMapping("/get")
    public ResultDto<List<UserEnvConfigResDto>> get(){
        List<UserEnvConfigResDto> res = userEnvConfigService.getConfigs();
        return ResultDto.createSuccess(res);
    }
}
