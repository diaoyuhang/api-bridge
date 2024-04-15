package com.api.bridge.controller;

import com.alibaba.fastjson2.JSONObject;
import com.api.bridge.dao.domain.User;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.user.UserReqDto;
import com.api.bridge.dto.user.UserResDto;
import com.api.bridge.service.UserService;
import com.api.bridge.utils.SecretUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResultDto<UserResDto> register(@RequestBody UserReqDto userReqDto) throws Exception {
        User user = userService.register(userReqDto.convertUser());
        UserResDto userResDto = UserResDto.create(user);
        userResDto.setAuthentication(SecretUtil.decrypt(JSONObject.toJSONString(user)));
        return ResultDto.createSuccess(userResDto);
    }
}
