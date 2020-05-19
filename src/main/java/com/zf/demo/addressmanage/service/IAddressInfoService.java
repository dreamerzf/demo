package com.zf.demo.addressmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zf.demo.utils.BaseVO;
import com.zf.demo.addressmanage.service.vo.AddressInfoVO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAddressInfoService {

    public ModelAndView page();

    public BaseVO<AddressInfoVO> findAddressInfos(BaseVO<AddressInfoVO> baseVO, AddressInfoVO addressInfoVO);

    public BaseVO<AddressInfoVO> insert(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO, HttpServletRequest request);

    public BaseVO<AddressInfoVO> delete(@RequestBody List<AddressInfoVO> addressInfoList);

    public BaseVO<AddressInfoVO> update(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO);

    public BaseVO<AddressInfoVO> findBySearchText(BaseVO<AddressInfoVO> baseVO, @ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO);

    public BaseVO<AddressInfoVO> getAddressInfo(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO);

}