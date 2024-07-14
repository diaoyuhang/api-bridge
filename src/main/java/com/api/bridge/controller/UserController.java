package com.api.bridge.controller;

import com.alibaba.fastjson2.JSONObject;
import com.api.bridge.constant.Status;
import com.api.bridge.dao.domain.User;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.user.UserReqDto;
import com.api.bridge.dto.user.UserResDto;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.dto.validGroup.Select;
import com.api.bridge.dto.validGroup.Update;
import com.api.bridge.service.UserService;
import com.api.bridge.utils.ReqThreadInfoUtil;
import com.api.bridge.utils.SecretUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResultDto<String> register(@RequestBody @Validated({Insert.class}) UserReqDto userReqDto)  {
        User user = userService.register(userReqDto.convertUser());
        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @PostMapping("/login")
    public ResultDto<UserResDto> login(@RequestBody @Validated({Select.class}) UserReqDto userReqDto){
        User user = userService.login(userReqDto.convertUser());
        UserResDto userResDto = UserResDto.create(user);
        userResDto.setAuthentication(SecretUtil.encrypt(JSONObject.toJSONString(user)));
        return ResultDto.createSuccess(userResDto);
    }

    @PostMapping("/editInfo")
    public ResultDto<String> editInfo(@RequestBody @Validated({Update.class}) UserReqDto userReqDto){
        User user = userService.editInfo(userReqDto);
        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @GetMapping("/getUserInfo")
    public ResultDto getUserInfo(){
        String userInfo = SecretUtil.decrypt(ReqThreadInfoUtil.getToken());
        return ResultDto.createSuccess(JSONObject.parseObject(userInfo,UserResDto.class));
    }
}
