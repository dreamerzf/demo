package com.zf.demo.addressmanage.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zf.demo.addressmanage.dao.entity.AddressInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.util.List;
/**
  *数据访问层接口
  */
@Mapper
@Component
public interface AddressInfoDao {

    public List<AddressInfoEntity> findAddressInfos(IPage<?> page, @Param("addressInfo") AddressInfoEntity addressInfoEntity);

    public int insert(AddressInfoEntity addressInfoEntity);

    public int delete(AddressInfoEntity addressInfo);

    public int update(AddressInfoEntity addressInfoEntity);

    public List<AddressInfoEntity> findBySearchText(AddressInfoEntity addressInfoEntity);

    public AddressInfoEntity getAddressInfo(AddressInfoEntity addressInfoEntity);

}