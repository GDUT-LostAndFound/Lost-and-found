package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDao extends BaseMapper<User> {
/**
 * 查询该手机号是否已被用户使用，是返回1，否返回0
 */
    @Select("select exists (select 1 from user where phone=#{phone} and id!=#{id})")
    Integer hasUsePhone(String phone,Integer id);
    /**
     * 查询该用户名是否已被用户使用，是返回1，否返回0
     */
    @Select("select exists (select 1 from user where username=#{username} and id!=#{id})")
    Integer hasUseUsername(String username,Integer id);
}
