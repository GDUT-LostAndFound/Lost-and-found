package com.example.controller;

import com.example.model.HostHolder;
import com.example.model.vo.SelectResult;
import com.example.model.vo.UpdateResult;
import com.example.model.dto.PostData;
import com.example.service.PostService;
import com.example.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@Api("帖子")
public class PostController {
    @Autowired
    PostService postService;

    @ApiOperation("发送帖子")
    @PostMapping("/api/post{pageId}")
    public UpdateResult sendPost(@RequestBody PostData postData, @PathVariable Integer pageId) {
        return postService.sendPost(postData, pageId);
    }

    @ApiOperation("查看失物")
    @GetMapping("/api/lostitems")
    public SelectResult<PostData> selectLostitems() {
        return postService.selectLostitems();
    }

    @ApiOperation("查看找到的物品")
    @GetMapping("/api/founditems")
    public SelectResult<PostData> selectFounditems() {
        return postService.selectFounditems();
    }
}
