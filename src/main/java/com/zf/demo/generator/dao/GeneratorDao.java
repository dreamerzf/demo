package com.zf.demo.generator.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.demo.generator.dao.entity.TableEntity;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * Create by zengfei
 * Date 2019/12/13 14:23
 */
@Mapper
@Component
public interface GeneratorDao {
    public List<TableEntity> getTables();
    public List<TableEntity> getAllColumns(Page<?> page, @Param("generator")TableEntity entity);
}
