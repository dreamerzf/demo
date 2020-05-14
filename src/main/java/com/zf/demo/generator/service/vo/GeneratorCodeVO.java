package com.zf.demo.generator.service.vo;

import javafx.scene.control.Tab;

import java.util.List;

/**
 * Create by zengfei
 * Date 2019/12/27 13:49
 */
public class GeneratorCodeVO {
    private String projectName;
    private String tableName;
    private String tableCommnet;
    private String moduleName;
    private String moduleComment;
    private String className;
    private String classComment;
    private String colList;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCommnet() {
        return tableCommnet;
    }

    public void setTableCommnet(String tableCommnet) {
        this.tableCommnet = tableCommnet;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleComment() {
        return moduleComment;
    }

    public void setModuleComment(String moduleComment) {
        this.moduleComment = moduleComment;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassComment() {
        return classComment;
    }

    public void setClassComment(String classComment) {
        this.classComment = classComment;
    }

    public String getColList() {
        return colList;
    }

    public void setColList(String colList) {
        this.colList = colList;
    }

    @Override
    public String toString() {
        return "GeneratorCodeVO{" +
                "projectName='" + projectName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableCommnet='" + tableCommnet + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", moduleComment='" + moduleComment + '\'' +
                ", className='" + className + '\'' +
                ", classComment='" + classComment + '\'' +
                ", colList='" + colList + '\'' +
                '}';
    }
}
