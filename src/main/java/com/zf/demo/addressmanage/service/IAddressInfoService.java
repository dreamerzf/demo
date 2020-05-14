package com.zf.demo.addressmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zf.demo.addressmanage.service.vo.AddressInfoVO;
import com.zf.demo.utils.BaseVO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAddressInfoService {

    public ModelAndView page();

    public IPage<AddressInfoVO> findAddressInfos(Page<AddressInfoVO> page, AddressInfoVO addressInfoVO);

    public BaseVO<AddressInfoVO> insert(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO, HttpServletRequest request);

    public int delete(@RequestBody List<AddressInfoVO> addressInfoList);

    public int update(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO);

    public IPage<AddressInfoVO> findBySearchText(Page<AddressInfoVO> page,@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO);

    public AddressInfoVO getAddressInfo(@ModelAttribute("addressInfoVO") AddressInfoVO addressInfoVO);

}