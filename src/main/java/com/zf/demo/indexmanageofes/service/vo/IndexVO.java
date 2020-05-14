package com.zf.demo.indexmanageofes.service.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Create by zengfei
 * Date 2020/4/28 14:25
 */
@Data
public class IndexVO {
    @Id
    private String indexName;
}
