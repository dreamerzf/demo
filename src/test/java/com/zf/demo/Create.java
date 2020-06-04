package com.zf.demo;

import com.zf.demo.generator.service.vo.Column;
import com.zf.demo.generator.service.vo.Table;

import java.util.List;

/**
 * Create by zengfei
 * Date 2020/5/19 16:48
 */
public class Create {
    private Table table;
    private List<Column> Clist;
    private String javaDir;
    private String htmlDir;
    private String jsDir;

    public Create(Table table, List<Column> Clist){
        this.Clist=Clist;
        this.table=table;
        this.javaDir ="sc";
    }
}
