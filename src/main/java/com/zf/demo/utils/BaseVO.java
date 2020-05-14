package com.zf.demo.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * Create by zengfei
 * Date 2020/4/15 9:29
 */
public class BaseVO<T> extends Page<T> {
    private String StringData;
    private String message;
    private String detail;
    private PageResult pageResult;
    private List<T> data;
    private boolean success=true;

    private String status="0000";

    public BaseVO(){}

    public BaseVO(Throwable e){
        this.success=false;
        this.status="9999";
        String message2 = e.getMessage();
        if(message2.length()>19){
            setMessage("系统错误，点击查看详情！");
            setDetail(message2);
        }else{
            setMessage("系统错误");
            setDetail(message2);
        }
    }
    public static BaseVO SUCCESS(){
        BaseVO baseVO = new BaseVO();
        baseVO.setMessage("成功");
        baseVO.setSuccess(true);
        baseVO.setStatus("0000");
        return baseVO;
    }
    public static BaseVO ERROR(){
        BaseVO baseVO = new BaseVO();
        baseVO.setMessage("失败");
        baseVO.setSuccess(false);
        baseVO.setStatus("9999");
        return baseVO;

    }
    public String getStringData() {
        return StringData;
    }

    public void setStringData(String stringData) {
        StringData = stringData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if(message != null && message.length()>19){
            this.message = "信息太长，请点击查看详情";
            this.detail=message;
        }else{

            this.message = message;
        }
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PageResult getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
