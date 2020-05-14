package com.zf.demo.indexmanageofes.service.impl;

import com.zf.demo.indexmanageofes.logic.IndexAL;
import com.zf.demo.indexmanageofes.service.IIndexService;
import com.zf.demo.indexmanageofes.service.vo.IndexVO;
import com.zf.demo.utils.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by zengfei
 * Date 2020/4/28 14:22
 */
@RestController
@RequestMapping(value = "/indexmanage")
public class IndexServiceImpl implements IIndexService {

    @Autowired
    private IndexAL indexAL;

    @Override
    @RequestMapping(value = "/addIndex",method = RequestMethod.POST)
    public BaseVO<IndexVO> addIndex(IndexVO vo) {
        return indexAL.addIndex(vo);
    }
}
