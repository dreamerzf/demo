package com.zf.demo.indexmanageofes.service;

import com.zf.demo.indexmanageofes.service.vo.IndexVO;
import com.zf.demo.utils.BaseVO;

/**
 * Create by zengfei
 * Date 2020/4/28 14:21
 */
public interface IIndexService {

    public BaseVO<IndexVO> addIndex(IndexVO vo);
}
