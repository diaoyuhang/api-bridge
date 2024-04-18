package com.api.bridge.service.impl;

import com.api.bridge.dao.ApiMetaDateDao;
import com.api.bridge.dao.TagGroupDao;
import com.api.bridge.dao.domain.ApiMetaDate;
import com.api.bridge.dao.domain.TagGroup;
import com.api.bridge.dto.api.ApiMetaDateReqDto;
import com.api.bridge.service.ApiMetaDateService;
import com.api.bridge.utils.UserHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiMetaDateServiceImpl implements ApiMetaDateService {

    @Autowired
    private TagGroupDao tagGroupDao;
    @Autowired
    private ApiMetaDateDao apiMetaDateDao;

    @Override
    public void addApiMetaDate(ApiMetaDateReqDto apiMetaDateReqDto) {
        TagGroup tagGroup = apiMetaDateReqDto.createTagGroup();
        TagGroup oldTagGroup = tagGroupDao.selectByPrimaryKey(tagGroup.getId());
        if (oldTagGroup == null){
            tagGroupDao.insert(tagGroup);
        }else{
            oldTagGroup.setName(tagGroup.getName());
            UserHelperUtil.fillEditInfo(oldTagGroup);
            tagGroupDao.updateByPrimaryKey(oldTagGroup);
        }

        List<ApiMetaDate> apiMetaDateList = apiMetaDateReqDto.createApiMetaDateList();
    }
}
