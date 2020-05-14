package com.zf.demo.usermangeofes.service.vo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Create by zengfei
 * Date 2020/4/26 16:17
 */
@Data
@Document(indexName = "mydocu",type = "user")
public class UserVO {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Integer sex;
}
