package com.zf.demo2.addressmanage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.demo2.addressmanage.dao.entity.AddressInfoEntity;
import com.zf.demo.utils.CastEnUtil;
import com.zf.demo2.addressmanage.logic.AddressInfoAL2;
import com.zf.demo2.addressmanage.service.IAddressInfoService2;
import com.zf.demo2.addressmanage.service.vo.AddressInfoVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 *@author
 *Service实现类
 */
@RestController
@RequestMapping(value="/addressmanage2")
public class AddressInfoService2Impl implements IAddressInfoService2 {
    @Autowired
    private AddressInfoAL2 addressInfoAL2;

    @Override
    @RequestMapping(value = "/addressInfoIndex")
    public ModelAndView page() {
        return new ModelAndView("addressmanage/AddressInfoIndex");
    }
    /**
     *
     *查询
     */
    @Override
    @RequestMapping(value="/findAddressInfos")
    public IPage<AddressInfoVO> findAddressInfos(Page<AddressInfoVO> page, AddressInfoVO addressInfoVO){
        //VO转换成Entity
        AddressInfoEntity addressInfoEntity = CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class);
        //分页查询，
        List<AddressInfoEntity> addressInfoList= addressInfoAL2.findAddressInfos(page,addressInfoEntity);
        if(!CollectionUtils.isEmpty(addressInfoList)){
            page.setRecords(CastEnUtil.convertPojos(addressInfoList,AddressInfoVO.class));
        }
        return page;
    }
    /**
     *
     *增加
     */
    @Override
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    @Transactional
    public int insert(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO, HttpServletRequest request){
        return addressInfoAL2.insert(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class));
    }
    /**
     *
     *删除
     */
    @Override
    @RequestMapping(value="/delete",method=RequestMethod.DELETE)
    @Transactional
    public int delete(@RequestBody List<AddressInfoVO> addressInfoList){
        boolean b=false;

        for(AddressInfoVO addressInfoVO:addressInfoList){
            b=false;
            if(1> addressInfoAL2.delete(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class))){
                b=true;
                break;
            }
        }
        if(b){
            return 1;
        }else{
            return -1;
        }
    }
    /**
     *
     *更新
     */
    @Override
    @RequestMapping(value="/update",method=RequestMethod.PUT)
    @Transactional
    public int update(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO){
        return addressInfoAL2.update(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class));
    }
    /**
     *
     *查看
     */
    @Override
    @RequestMapping(value="/findBySearchText",method=RequestMethod.GET)
    @Transactional
    public IPage<AddressInfoVO> findBySearchText(Page<AddressInfoVO> page , @ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO){
        //VO转换成Entity
        AddressInfoEntity addressInfoEntity = CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class);
        //分页查询，
        List<AddressInfoEntity> addressInfoList= addressInfoAL2.findBySearchText(page,addressInfoEntity);
        if(!CollectionUtils.isEmpty(addressInfoList)){
            page.setRecords(CastEnUtil.convertPojos(addressInfoList,AddressInfoVO.class));
        }
        return page;
    }
    /**
     *
     *查看
     */
    @Override
    @RequestMapping(value="/AddressInfo",method=RequestMethod.POST)
    @Transactional
    public AddressInfoVO getAddressInfo(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO){
        return CastEnUtil.convertPojo(addressInfoAL2.getAddressInfo(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class)),AddressInfoVO.class);
    }
}