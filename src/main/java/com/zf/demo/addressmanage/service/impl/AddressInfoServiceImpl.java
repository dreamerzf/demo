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
    public BaseVO<AddressInfoVO> findAddressInfos(BaseVO<AddressInfoVO> baseVO,AddressInfoVO addressInfoVO){
        //VO转换成Entity
        AddressInfoEntity addressInfoEntity = CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class);
        //分页查询，
        List<AddressInfoEntity> addressInfoList=addressInfoAL.findAddressInfos(baseVO,addressInfoEntity);
        if(!CollectionUtils.isEmpty(addressInfoList)){
            baseVO.setRecords(CastEnUtil.convertPojos(addressInfoList,AddressInfoVO.class));
        }
        return baseVO;
    }
    /**
     *
     *增加
     */
    @Override
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    @Transactional
    public BaseVO<AddressInfoVO> insert(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO,HttpServletRequest request){
        BaseVO<AddressInfoVO> baseVO = new BaseVO<>();
            try{
                int i=addressInfoAL.insert(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class));
                if(i==1){
                    baseVO.setStatus("0000");
                    baseVO.setSuccess(true);
                    baseVO.setMessage("新增成功");
                }else{
                    baseVO.setStatus("9999");
                    baseVO.setSuccess(false);
                    baseVO.setMessage("新增失败");
                }
            }catch (Exception e){
                baseVO.setStatus("9999");
                baseVO.setSuccess(false);
                baseVO.setMessage("新增异常："+e.getMessage());
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
    public BaseVO<AddressInfoVO> delete(@RequestBody List<AddressInfoVO> addressInfoList){
        BaseVO<AddressInfoVO> baseVO = new BaseVO<>();
        int i=0;
        try{
            boolean b=true;
            for(AddressInfoVO addressInfoVO:addressInfoList){
                b=true;
                i++;
                if(1>addressInfoAL.delete(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class))){
                    b=false;
                    break;
                }
            }
            if(b){
                baseVO.setStatus("0000");
                baseVO.setSuccess(true);
                baseVO.setMessage("删除成功");
            }else{
                baseVO.setStatus("9999");
                baseVO.setSuccess(false);
                baseVO.setMessage("第"+i+"条记录删除失败");
            }
        }catch (Exception e){
            baseVO.setStatus("9999");
            baseVO.setSuccess(false);
            baseVO.setMessage("第"+i+"条记录删除异常："+e.getMessage());
        }
        return baseVO;
    }
    /**
     *
     *更新
     */
    @Override
    @RequestMapping(value="/update",method=RequestMethod.PUT)
    @Transactional
    public BaseVO<AddressInfoVO> update(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO){
        BaseVO<AddressInfoVO> baseVO = new BaseVO<>();
        try{
            int i=addressInfoAL.update(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class));
            if(i==1){
                baseVO.setStatus("0000");
                baseVO.setSuccess(true);
                baseVO.setMessage("修改成功");
            }else{
                baseVO.setStatus("9999");
                baseVO.setSuccess(false);
                baseVO.setMessage("修改失败");
            }
        }catch (Exception e){
            baseVO.setStatus("9999");
            baseVO.setSuccess(false);
            baseVO.setMessage("修改异常："+e.getMessage());
        }
        return baseVO;
    }
    /**
     *
     *查看
     */
    @Override
    @RequestMapping(value="/findBySearchText",method=RequestMethod.POST)
    @Transactional
    public BaseVO<AddressInfoVO> findBySearchText(BaseVO<AddressInfoVO> baseVO ,@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO){
        //VO转换成Entity
        AddressInfoEntity addressInfoEntity = CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class);
        //分页查询，
        List<AddressInfoEntity> addressInfoList=addressInfoAL.findBySearchText(baseVO,addressInfoEntity);
        if(!CollectionUtils.isEmpty(addressInfoList)){
            baseVO.setRecords(CastEnUtil.convertPojos(addressInfoList,AddressInfoVO.class));
        }
        return baseVO;
    }
    /**
     *
     *查看
     */
    @Override
    @RequestMapping(value="/getAddressInfo",method=RequestMethod.POST)
    @Transactional
    public BaseVO<AddressInfoVO> getAddressInfo(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO){
        BaseVO<AddressInfoVO> baseVO = new BaseVO<>();
        AddressInfoEntity addressInfoEntity = addressInfoAL.getAddressInfo(CastEnUtil.convertPojo(addressInfoVO,AddressInfoEntity.class));
        if(addressInfoEntity !=null){
            List<AddressInfoVO> addressInfoList=new ArrayList<>();
            addressInfoList.add(CastEnUtil.convertPojo(addressInfoEntity,AddressInfoVO.class));
            baseVO.setRecords(addressInfoList);
            baseVO.setMessage("查询成功");
            baseVO.setSuccess(true);
            baseVO.setStatus("0000");
        }else{
            baseVO.setMessage("该条记录不存在");
            baseVO.setSuccess(false);
            baseVO.setStatus("9999");
        }
        return baseVO;

    }
}