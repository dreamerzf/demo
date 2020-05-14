package com.zf.demo.generator.service.vo;

/**
 * Create by zengfei
 * Date 2019/12/30 13:54
 */
public class Column {
    String entityName;
    String tableName;
    String tableComment;
    String columnName;
    String columnComment;
    String fieldName;
    String fieldDesc;
    String fieldType;
    String dataType;
    String columnKey;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
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

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    @Override
    public String toString() {
        return "Column{" +
                "entityName='" + entityName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldDesc='" + fieldDesc + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", dataType='" + dataType + '\'' +
                ", columnKey='" + columnKey + '\'' +
                '}';
    }
}
