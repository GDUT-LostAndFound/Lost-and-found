package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.dto.PostData;
import com.example.model.entity.Post;
import com.example.model.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface PostDao extends BaseMapper<Post> {
    @Select("select image,name,phone,description,location from post where user_id=#{userId} and type=#{type}")
    public ArrayList<PostData> selectPosts(Integer userId, Integer type);
}
