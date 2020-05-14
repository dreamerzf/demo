package com.zf.demo2.addressmanage.logic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.demo2.addressmanage.dao.entity.AddressInfoEntity;
import com.zf.demo2.addressmanage.dao.AddressInfoDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Create by zengfei
 * Date Fri Apr 24 16:50:08 CST 2020
 */
@Component
public class AddressInfoAL2 {
    @Autowired
    private AddressInfoDao2 addressInfoDao2;

    /**
     *分页查询
     *@param page
     *@param entity
     *return
     */
    public List<AddressInfoEntity> findAddressInfos(Page<?> page,AddressInfoEntity entity){
        List<AddressInfoEntity> list=addressInfoDao2.findAddressInfos(page,entity);
        return list;
    }
    /**
     *删除
     *@param entity
     *return
     */
    public int delete(AddressInfoEntity entity){
        return addressInfoDao2.delete(entity);
    }
    /**
     *修改
     *@param entity
     *return
     */
    public int update(AddressInfoEntity entity){
        return addressInfoDao2.update(entity);
    }
    /**
     *插入
     *@param entity
     *return
     */
    public int insert(AddressInfoEntity entity){
        return addressInfoDao2.insert(entity);
    }

    /**
     *获取
     *@param entity
     *return
     */
    public List<AddressInfoEntity> findBySearchText(Page<?> page ,AddressInfoEntity entity){
        return addressInfoDao2.findBySearchText(entity);
    }
    /**
     *获取
     *@param entity
     *return
     */
    public AddressInfoEntity getAddressInfo(AddressInfoEntity entity){
        return addressInfoDao2.getAddressInfo(entity);
    }
}
