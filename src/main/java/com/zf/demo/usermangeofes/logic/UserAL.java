package com.zf.demo.usermangeofes.logic;

import com.zf.demo.usermangeofes.dao.UserDao;
import com.zf.demo.usermangeofes.dao.entity.UserEntity;
import com.zf.demo.usermangeofes.service.vo.UserVO;
import com.zf.demo.utils.CastEnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Create by zengfei
 * Date 2020/4/26 16:26
 */
@Component
public class UserAL {
    @Autowired
    private UserDao userDao;
    public UserVO addUser(UserVO userVO){
        UserEntity userEntity= CastEnUtil.convertPojo(userVO,UserEntity.class);
        return CastEnUtil.convertPojo(userDao.save(userEntity),UserVO.class);

    }

    public UserVO findById(String id){
        Optional<UserEntity> userEntity=userDao.findById(id);

        return CastEnUtil.convertPojo(userEntity.get(),UserVO.class);
    }
}
