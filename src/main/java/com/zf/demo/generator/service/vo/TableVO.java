package com.zf.demo.generator.service.vo;

/**
 * Create by zengfei
 * Date 2019/12/13 11:32
 */
public class TableVO {
    //    java类英文名称
    private String entityName;
    //    列英文
    private String columnName;
    //    java属性名
    private String fieldName;
    //    表名
    private String tableName;
    //    表中文描述
    private String tableComment;
    //    java属性中文描述
    private String fieldDesc;
    //    数据库列类型
    private String dataType;
    //    java属性类型
    private String fieldType;
    //    主键类型
    private String columnKey;
    //    列描述
    private String columnComment;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    @Override
    public String toString() {
        return "TableVO{" +
                "entityName='" + entityName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", fieldDesc='" + fieldDesc + '\'' +
                ", dataType='" + dataType + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", columnComment='" + columnComment + '\'' +
                '}';
    }
}
