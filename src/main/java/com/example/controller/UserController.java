package com.example.controller;

import com.example.model.vo.UpdateResult;
import com.example.model.dto.UserDto;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Api("用户")
//@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("更改用户信息")
    @PutMapping("/api/update")
    public UpdateResult update(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

}
