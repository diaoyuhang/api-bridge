package com.api.bridge.controller;

import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.api.ApiMetaDateReqDto;
import com.api.bridge.service.ApiMetaDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiMetaDateService apiMetaDateService;
    @PostMapping("/acceptApiMetaDate")
    public ResultDto<String> acceptApiMetaDate(@RequestBody @Validated ApiMetaDateReqDto apiMetaDateReqDto) {
        apiMetaDateService.addApiMetaDate(apiMetaDateReqDto);
        return ResultDto.createSuccess("success");
    }
}
