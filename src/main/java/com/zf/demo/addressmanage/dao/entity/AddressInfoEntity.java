package com.zf.demo.addressmanage.dao.entity;

/**
 * VO实体类
 */
public class AddressInfoEntity{

     private String searchText;
         /** n*/
    private Integer n;

    /** m*/
    private String m;


     
    /**
     *获取编号的值
     *@return Integer
     */
     public Integer getN(){
        return n;
     }

     /**
      *设置编号的值
      *@paramter Integer n
      */
      public void setN(Integer n){
        this.n = n;
      }

    /**
     *获取信息的值
     *@return String
     */
     public String getM(){
        return m;
     }

     /**
      *设置信息的值
      *@paramter String m
      */
      public void setM(String m){
        this.m = m;
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
                sb.append("; n=" + (n) == null ?"null" : n.toString());
        sb.append("; m=" + (m) == null ?"null" : m.toString());

        return sb.toString();
      }
}