package com.api.bridge.controller;

import com.alibaba.fastjson2.JSONObject;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.api.ApiMetaDateReqDto;
import com.api.bridge.service.ApiMetaDateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;
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
    @RequestMapping("/")
    public ResultDto<String> acceptApiMetaDate(@RequestBody @Validated ApiMetaDateReqDto apiMetaDateReqDto) {
        apiMetaDateService.addApiMetaDate(apiMetaDateReqDto);
        return ResultDto.createSuccess("success");
    }

}
