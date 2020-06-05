package com.zf.demo.addressmanage.logic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.demo.addressmanage.dao.AddressInfoDao;
import com.zf.demo.addressmanage.dao.entity.AddressInfoEntity;

import com.zf.demo.addressmanage.oradao.AddressInfoDao2;
import com.zf.demo.utils.CastEnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.SchemaOutputResolver;
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

    @Autowired
    private AddressInfoDao2 addressInfoDao2;

    /**
     *分页查询
     *@param page
     *@param entity
     *return
     */
    public List<AddressInfoEntity> findAddressInfos(Page<?> page,AddressInfoEntity entity){
        com.zf.demo.addressmanage.oradao.entity.AddressInfoEntity en=new com.zf.demo.addressmanage.oradao.entity.AddressInfoEntity();
        System.out.println(addressInfoDao2.findAddressInfos(page,en));
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
