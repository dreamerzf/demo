package com.zf.demo.addressmanage.dao.entity;

/**
 * VO实体类
 */
public class AddressInfoEntity{

     private String searchText;
         /** m*/
    private String m;

    /** n*/
    private Integer n;

    /**检索字段
    private String searchText;


     
    /**
     *获取地址信息的值
     *@return String
     */
     public String getM(){
        return m;
     }

     /**
      *设置地址信息的值
      *@paramter String m
      */
      public void setM(String m){
        this.m = m;
      }

    /**
     *获取地址编号的值
     *@return Integer
     */
     public Integer getN(){
        return n;
     }

     /**
      *设置地址编号的值
      *@paramter Integer n
      */
      public void setN(Integer n){
        this.n = n;
      }
    /**
     * 获取检索字段的值
     * @return String
     */
    public String getSearchText(){
        return searchText;
    }
    /**
     * 设置检索字段的值
     * @return void
     */
    public void setSearchText(String searchText){
        this.searchText = searchText;
    }


     /**
      *toString方法
      */
      public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append(this.getClass().getName());
                sb.append("; m=" + (m) == null ?"null" : m.toString());
        sb.append("; n=" + (n) == null ?"null" : n.toString());

        return sb.toString();
      }
}