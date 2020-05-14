package com.zf.demo;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by zengfei
 * Date 2020/4/30 16:45
 */
@Data
public class Day implements Serializable {
    private String openId;
    private String daysId;
    //每天的标题
    private String title;
    //代办事项的数量
    private int itemNumber;
    //日程
    private String date;
}
