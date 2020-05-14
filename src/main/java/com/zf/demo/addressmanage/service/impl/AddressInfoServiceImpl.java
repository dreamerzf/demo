package com.zf.demo.addressmanage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zf.demo.utils.BaseVO;
import org.springframework.transaction.annotation.Transactional;
import com.zf.demo.addressmanage.dao.entity.AddressInfoEntity;
import com.zf.demo.addressmanage.logic.AddressInfoAL;
import com.zf.demo.addressmanage.service.IAddressInfoService;
import com.zf.demo.addressmanage.service.vo.AddressInfoVO;
import com.zf.demo.utils.CastEnUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 *@author
 *Service实现类
 */
@RestController
@RequestMapping(value="/addressmanage")
public class AddressInfoServiceImpl implements IAddressInfoService{
    @Autowired
    private AddressInfoAL addressInfoAL;

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
    public IPage<AddressInfoVO> findAddressInfos(Page<AddressInfoVO> page,AddressInfoVO addressInfoVO){
        //VO转换成Entity
        AddressInfoEntity addressInfoEntity = CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class);
        //分页查询，
        List<AddressInfoEntity> addressInfoList=addressInfoAL.findAddressInfos(page,addressInfoEntity);
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
    public BaseVO<AddressInfoVO> insert(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO, HttpServletRequest request){
        BaseVO<AddressInfoVO> baseVO = new BaseVO<AddressInfoVO>();
        try{
            int i=addressInfoAL.insert(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class));
            if (i==1){
                baseVO.setSuccess(true);
                baseVO.setStatus("0000");
                baseVO.setMessage("新增数据成功");
            }else{
                baseVO.setSuccess(false);
                baseVO.setStatus("9999");
                baseVO.setMessage("新增数据失败");
            }
        }catch (Exception e){
            baseVO.setSuccess(false);
            baseVO.setStatus("9999");
            baseVO.setMessage("新增操作异常");
        }
        return baseVO;
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
            if(1>addressInfoAL.delete(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class))){
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
        return addressInfoAL.update(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class));
    }
    /**
     *
     *查看
     */
    @Override
    @RequestMapping(value="/findBySearchText",method=RequestMethod.GET)
    @Transactional
    public IPage<AddressInfoVO> findBySearchText(Page<AddressInfoVO> page ,@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO){
        //VO转换成Entity
        AddressInfoEntity addressInfoEntity = CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class);
        //分页查询，
        List<AddressInfoEntity> addressInfoList=addressInfoAL.findBySearchText(page,addressInfoEntity);
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
    @RequestMapping(value="/getAddressInfo",method=RequestMethod.POST)
    @Transactional
    public AddressInfoVO getAddressInfo(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO){
        return CastEnUtil.convertPojo(addressInfoAL.getAddressInfo(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class)),AddressInfoVO.class);
    }
}