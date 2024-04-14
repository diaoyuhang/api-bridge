package com.api.bridge.controller;

import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.api.ApiMetaDateReqDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/acceptApiMetaDate")
    public ResultDto<String> acceptApiMetaDate(@RequestBody ApiMetaDateReqDto apiMetaDateReqDto) {

        return ResultDto.createSuccess("success");
    }
}
