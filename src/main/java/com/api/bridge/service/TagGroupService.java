package com.api.bridge.service;

import com.api.bridge.dto.tag.TagGroupResDto;

import java.util.List;

public interface TagGroupService {
    List<TagGroupResDto> getTagGroupList(Long projectId);
}
