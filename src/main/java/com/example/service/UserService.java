package com.example.service;


import com.example.dao.UserDao;
import com.example.model.HostHolder;
import com.example.model.vo.UpdateResult;
import com.example.model.dto.UserDto;
import com.example.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    UserDao userDao;

    public UpdateResult update(UserDto userDto) {
        //用户名是否已被其他用户使用
        if (userDao.hasUseUsername(userDto.getUsername(), HostHolder.getUserId()) == 1) {
            return new UpdateResult(1, "用户名已被其他用户使用");
        }
        //手机号格式校验
        if (!userDto.getPhone().matches("^1[3-9]\\d{9}$")) {
            return new UpdateResult(1, "手机号格式错误");
        }
        //手机号是否已被其他用户使用
        if (userDao.hasUsePhone(userDto.getPhone(), HostHolder.getUserId()) == 1) {
            return new UpdateResult(1, "手机号已被其他用户使用");
        }
        User user = toUser(userDto);
        userDao.updateById(user);
        return new UpdateResult(0, "更新成功");

    }

    private User toUser(UserDto userDto) {
        return new User(HostHolder.getUserId(), userDto.getUsername(), userDto.getPassword(), userDto.getAvatar(), userDto.getPhone());
    }
}
