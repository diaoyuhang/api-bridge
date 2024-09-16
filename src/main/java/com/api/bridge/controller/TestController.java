package com.api.bridge.controller;

import com.api.bridge.dto.ResultDto;
import com.api.bridge.utils.SecretUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
@Tag(name="测试接口",description = "177e16ad-008a-4b21-b357-f2711af12bde")
public class TestController {

    @GetMapping("/decoder")
    @Operation(summary = "解密")
    public String decoder(String encryptedData){
        return SecretUtil.decrypt(encryptedData);
    }

    @GetMapping("/dateTime")
    @Operation(summary = "服务器时间")
    public ResultDto<Date> dateTime(){
        return ResultDto.createSuccess(new Date());
    }
}
