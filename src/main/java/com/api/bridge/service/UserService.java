package com.api.bridge.service;

import com.api.bridge.dao.domain.User;
import com.api.bridge.dto.user.UserReqDto;

public interface UserService {
    User register(User user);

    User login(User user);

    User editInfo(UserReqDto userReqDto);
}
