package com.api.bridge.controller;

import com.api.bridge.dto.ResultDto;
import com.api.bridge.utils.SecretUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/decoder")
    public String decoder(String encryptedData){
        return SecretUtil.decrypt(encryptedData);
    }

    @GetMapping("/dateTime")
    public ResultDto<Date> dateTime(){
        return ResultDto.createSuccess(new Date());
    }
}
