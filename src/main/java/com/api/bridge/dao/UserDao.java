package com.api.bridge.dao;


import com.api.bridge.dao.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User record);

    int selectCountByEmail(String email);

    User selectByEmailAndPassword(User user);
}