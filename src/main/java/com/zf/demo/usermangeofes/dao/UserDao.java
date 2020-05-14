package com.zf.demo.usermangeofes.dao;

import com.zf.demo.usermangeofes.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Create by zengfei
 * Date 2020/4/26 16:22
 */
public interface UserDao extends CrudRepository<UserEntity,String> {

}
