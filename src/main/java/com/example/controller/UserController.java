package com.example.controller;

import com.example.model.Result;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@Api("用户")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("修改用户密码")
    @PutMapping("updatePwd/{password}")
    public Result<Object> updatePwd(@PathVariable @NotBlank
                                    @Size(min = 4, max = 16, message = "密码长度不小于{min}位且不超过{max}位")
                                    String password) {
        return userService.updatePwd(password);
    }

}
