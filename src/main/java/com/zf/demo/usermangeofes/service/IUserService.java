package com.zf.demo.usermangeofes.service;

import com.zf.demo.usermangeofes.service.vo.UserVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Create by zengfei
 * Date 2020/4/26 16:15
 */
public interface IUserService {

    public UserVO addUser(@RequestBody UserVO userVO);

    public UserVO findById(String id);
}
