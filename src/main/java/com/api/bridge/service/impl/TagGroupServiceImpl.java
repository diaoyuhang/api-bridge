package com.api.bridge.service.impl;

import com.api.bridge.dao.TagGroupDao;
import com.api.bridge.dao.domain.TagGroup;
import com.api.bridge.dto.tag.TagGroupResDto;
import com.api.bridge.service.TagGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagGroupServiceImpl implements TagGroupService {
    @Autowired
    private TagGroupDao tagGroupDao;

    @Override
    public List<TagGroupResDto> getTagGroupList(Long projectId) {
        List<TagGroup> tagGroupList = tagGroupDao.selectByProjectId(projectId);

        return tagGroupList.stream().map(t->TagGroupResDto.create(t)).collect(Collectors.toList());
    }
}
