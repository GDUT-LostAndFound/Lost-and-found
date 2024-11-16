package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.User;
import org.apache.ibatis.annotations.Update;

public interface UserDao extends BaseMapper<User> {
    /**
     * 修改密码
     *
     * @param password
     * @param id
     */
    @Update("update user set password=#{password} where id=#{id}")
    void updatePwd(String password, Integer id);
}
