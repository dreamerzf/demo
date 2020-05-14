package com.zf.demo.usermangeofes.service.impl;

import com.zf.demo.usermangeofes.logic.UserAL;
import com.zf.demo.usermangeofes.service.IUserService;
import com.zf.demo.usermangeofes.service.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by zengfei
 * Date 2020/4/26 16:20
 */
@RestController
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserAL userAL;
    @Override
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public UserVO addUser(UserVO userVO) {
        return userAL.addUser(userVO);
    }

    @Override
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public UserVO findById(String id) {
        return userAL.findById(id);
    }
}
