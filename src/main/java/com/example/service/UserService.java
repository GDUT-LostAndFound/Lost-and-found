package com.example.service;


import com.example.dao.UserDao;
import com.example.model.HostHolder;
import com.example.model.Result;
import com.example.util.EncodeUtil;
import com.example.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    UserDao userDao;

    /**
     * 修改密码
     *
     * @param password
     * @return
     */
    public Result<Object> updatePwd(String password) {
        userDao.updatePwd(EncodeUtil.encrypt(password), HostHolder.getUserId());
        return ResultUtil.success("密码更新成功");
    }
}
