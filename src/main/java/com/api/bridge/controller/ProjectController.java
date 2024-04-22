package com.api.bridge.controller;

import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.project.ProjectReqDto;
import com.api.bridge.dto.project.ProjectResDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @PostMapping("/createProject")
    public ResultDto<ProjectResDto> createProject(@RequestBody @Validated ProjectReqDto projectReqDto){

        return null;
    }
}
