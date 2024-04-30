package com.api.bridge.dto.tag;

import com.api.bridge.dao.domain.TagGroup;
import com.api.bridge.utils.SecretUtil;

public class TagGroupResDto {

    private String id;
    private String name;

    public static TagGroupResDto create(TagGroup tagGroup) {
        TagGroupResDto tagGroupResDto = new TagGroupResDto();
        tagGroupResDto.setId(tagGroup.getId());
        tagGroupResDto.setName(tagGroup.getName());
        return tagGroupResDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
