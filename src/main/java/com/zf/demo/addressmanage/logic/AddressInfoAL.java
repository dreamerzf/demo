package com.zf.demo.addressmanage.logic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.demo.addressmanage.dao.AddressInfoDao;
import com.zf.demo.addressmanage.dao.entity.AddressInfoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zengfei
 * Date Sat May 16 10:53:35 CST 2020
 */
@Component
public class AddressInfoAL {
    @Autowired
    private AddressInfoDao addressInfoDao;

    /**
     *分页查询
     *@param page
     *@param entity
     *return
     */
    public List<AddressInfoEntity> findAddressInfos(Page<?> page,AddressInfoEntity entity){
        List<AddressInfoEntity> list=addressInfoDao.findAddressInfos(page,entity);
        return list;
    }
    /**
     *删除
     *@param entity
     *return
     */
    public int delete(AddressInfoEntity entity){
        return addressInfoDao.delete(entity);
    }
    /**
     *修改
     *@param entity
     *return
     */
    public int update(AddressInfoEntity entity){
        return addressInfoDao.update(entity);
    }
    /**
     *插入
     *@param entity
     *return
     */
    public int insert(AddressInfoEntity entity){
        return addressInfoDao.insert(entity);
    }

    /**
     *获取
     *@param entity
     *return
     */
    public List<AddressInfoEntity> findBySearchText(Page<?> page ,AddressInfoEntity entity){
        return addressInfoDao.findBySearchText(page,entity);
    }
    /**
     *获取
     *@param entity
     *return
     */
    public AddressInfoEntity getAddressInfo(AddressInfoEntity entity){
        return addressInfoDao.getAddressInfo(entity);
    }
}
