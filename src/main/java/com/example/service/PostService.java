package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dao.PostDao;
import com.example.model.HostHolder;
import com.example.model.vo.SelectResult;
import com.example.model.vo.UpdateResult;
import com.example.model.dto.PostData;
import com.example.model.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {
    @Autowired
    PostDao postDao;

    public UpdateResult sendPost(PostData postData, Integer pageId) {
        //无效,pageId=0表寻找失主，1表寻找失物
        if (pageId != 0 && pageId != 1) {
            return new UpdateResult(1, "pageId无效");
        }
        postDao.insert(toPost(postData, pageId));
        return new UpdateResult(0, "更新成功");

    }
   public SelectResult<PostData>selectLostitems(){
       ArrayList<PostData> postData = postDao.selectPosts(HostHolder.getUserId(), 1);
       return new SelectResult<>(postData);
   }
    public SelectResult<PostData> selectFounditems() {
        ArrayList<PostData> postData = postDao.selectPosts(HostHolder.getUserId(), 0);
        return new SelectResult<>(postData);
    }
    private Post toPost(PostData postData, Integer pageId) {
        Post post = new Post(null, postData.getImage(),
                postData.getName(), postData.getPhone(),
                postData.getDescription(), postData.getLocation(), pageId, HostHolder.getUserId());
        return post;
    }


}
