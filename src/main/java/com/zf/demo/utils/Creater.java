package com.zf.demo.utils;

import com.zf.demo.generator.service.vo.Column;
import com.zf.demo.generator.service.vo.GeneratorCodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Create by zengfei
 * Date 2019/12/30 11:20
 */
public class Creater {
    protected static final Logger logger=LoggerFactory.getLogger(Creater.class);
    private static final String classPath= com.zf.demo.utils.PathUtils.getClassPath(Creater.class)+"/";
    GeneratorCodeVO generatorCodeVO=null;
    List<Column> columnList=null;

    String baseDir="";
    String htmlBaseDir="";
    String jsBaseDir="";

    public Creater() {
    }
    public Creater(GeneratorCodeVO codeVO,List<Column> list){
        this.generatorCodeVO=codeVO;
        this.columnList=list;
        this.baseDir=classPath+"/"+generatorCodeVO.getProjectName()
                +ConfigPropertiesUtils.getPropertyByName("urlconfig","baseDir")+"/";
        this.htmlBaseDir=classPath+"/"+generatorCodeVO.getProjectName()
                +ConfigPropertiesUtils.getPropertyByName("urlconfig","htmlBaseDir");
        this.jsBaseDir=classPath+"/"+generatorCodeVO.getProjectName()
                +ConfigPropertiesUtils.getPropertyByName("urlconfig","jsBaseDir");
    }

    public void createZip(){
        ZipCompressor zipCompressor=new ZipCompressor(classPath+"/"+generatorCodeVO.getClassName()+".zip");
        this.createJava();
        this.createResources();
        if(logger.isInfoEnabled()){
            logger.info(classPath+"/"+generatorCodeVO.getProjectName()+"/");
        }
        zipCompressor.compress(classPath+"/"+generatorCodeVO.getProjectName()+"/");
    }
    private void createJava(){
        this.createAL();
        this.createServiceImpl();
        this.createIservice();
        this.createVO();
        this.createEntity();
        this.createDao();
        this.createMapper();
    }
    private void createResources(){
        this.createIndexHtml();
        this.createSearchDlgHtml();
        this.createCreteDlgHtml();
        this.createDeleteDlgHtml();
        this.createEditDlgHtml();
        this.createShowDlgHtml();
        this.createJs();
    }
    public String createIndexHtml(){
        String filePath="static/temp/index.tlp";
        String dirPath="/";
        String type="Index.html";
        String content=this.getHtmlContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createSearchDlgHtml(){
        String filePath="static/temp/search.tlp";
        String dirPath="/";
        String type="Search.html";
        String content = this.getHtmlContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createCreteDlgHtml(){
        String filePath="static/temp/createDlg.tlp";
        String dirPath="/";
        String type="Create.html";
        String content = this.getHtmlContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createDeleteDlgHtml(){
        String filePath="static/temp/deleteDlg.tlp";
        String dirPath="/";
        String type="Delete.html";
        String content = this.getHtmlContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createEditDlgHtml(){
        String filePath="static/temp/editDlg.tlp";
        String dirPath="/";
        String type="Edit.html";
        String content = this.getHtmlContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createShowDlgHtml(){
        String filePath="static/temp/showDlg.tlp";
        String dirPath="/";
        String type="Show.html";
        String content = this.getHtmlContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createJs(){
        String filePath="static/temp/js.tlp";
        String dirPath="/";
        String type=".js";
        String content = this.getHtmlContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String getHtmlContent(String filePath,GeneratorCodeVO generatorCodeVO,String dirPath,String type,boolean isVOorEntity,boolean isMapper) {
        String content = "";
        try{
            content=CommonUtils.readResourceFile(filePath);
            content=CommonUtils.parseTemplate(content,"ProjectName",generatorCodeVO.getProjectName());
            content=CommonUtils.parseTemplate(content,"EntityName",generatorCodeVO.getClassName());
            content=CommonUtils.parseTemplate(content,"ModuleName",generatorCodeVO.getModuleName());
            content=CommonUtils.parseTemplate(content,"NowDate",(new Date()).toString());
            content=CommonUtils.parseTemplate(content,"EntityDesc",this.generatorCodeVO.getTableCommnet());
            content=CommonUtils.parseTemplate(content,"LcEntityName",this.generatorCodeVO.getClassName().toLowerCase());
            content=CommonUtils.parseTemplate(content,"LowercaseEntityName",CommonUtils.firstCharToLowerCase(this.generatorCodeVO.getClassName()));
            if(type=="Search.html"){
                content=CommonUtils.parseTemplate(content,"searchFieldList",getSearchFieldList());
            }
            if(type=="Create.html"){
                content=CommonUtils.parseTemplate(content,"createFieldList",getCreateFieldList());
            }
            if(type=="Edit.html"){
                content=CommonUtils.parseTemplate(content,"editFieldList",getEditFieldList());
            }
            if(type=="Show.html"){
                content=CommonUtils.parseTemplate(content,"showFieldList",getShowFieldList());
            }
            if(type==".js"){
                content=CommonUtils.parseTemplate(content,"resetSearchList",getResetSearchList());
                content=CommonUtils.parseTemplate(content,"fieldJsonList",getFieldJsonList());
                content=CommonUtils.parseTemplate(content,"createParaList",getCreateParaList());
                content=CommonUtils.parseTemplate(content,"editParaList",getEditParaList());
                content=CommonUtils.parseTemplate(content,"searchParaList",getSearchParaList());
                content=CommonUtils.parseTemplate(content,"resetCreateList",getResetCreateList());
                content=CommonUtils.parseTemplate(content,"showFieldList",getShowFieldList());
                content=CommonUtils.parseTemplate(content,"assignEditList",getAssignEditList());
                content=CommonUtils.parseTemplate(content,"pkAssignEdit",getPKAssignEdit());
                content=CommonUtils.parseTemplate(content,"assignShowList",getAssignShowList());
                content=CommonUtils.parseTemplate(content,"pkAssignShow",getPkAssignShow());
                content=CommonUtils.parseTemplate(content,"pkAssignList",getPkAssignList());
                content=CommonUtils.parseTemplate(content,"showFieldPartList",getShowFieldPartList());
                content=CommonUtils.parseTemplate(content,"showEditList",getShowEditList());
                content=CommonUtils.parseTemplate(content,"setShowEditValue",setShowEditValue());
                content=CommonUtils.parseTemplate(content,"deleteByPrimaryKeyList",deleteByPrimaryKeyList());
                content=CommonUtils.parseTemplate(content,"resetList",resetList());
            }
            String dir="";
            if(type==".js"){
                dir = this.jsBaseDir+generatorCodeVO.getModuleName()+dirPath;
            }else{
                dir = this.htmlBaseDir+generatorCodeVO.getModuleName()+dirPath;
            }
            FileUtils.createDir(dir);
            CommonUtils.writeContentToFile(dir+generatorCodeVO.getClassName()+type,content );
        }catch (Exception e){
            e.printStackTrace();
            content=type+"生成失败:"+e.getMessage();
        }
        return content;
    }
    private String getJavaContent(String filePath,GeneratorCodeVO generatorCodeVO,String dirPath,String type,boolean isVOorEntity,boolean isMapper){
        String content="";
        try{
            content=CommonUtils.readResourceFile(filePath);
            content=CommonUtils.parseTemplate(content,"ProjectName",generatorCodeVO.getProjectName());
            content=CommonUtils.parseTemplate(content,"EntityName",generatorCodeVO.getClassName());
            content=CommonUtils.parseTemplate(content,"ModuleName",generatorCodeVO.getModuleName());
            content=CommonUtils.parseTemplate(content,"NowDate",(new Date()).toString());
            if(isVOorEntity){
                content=CommonUtils.parseTemplate(content,"attr_list",createAttrList());
                content=CommonUtils.parseTemplate(content,"attr_getset_list",createAttrGetsetList());
                content=CommonUtils.parseTemplate(content,"attr_tostring_list",createAttrToStringList());
            }
            content=CommonUtils.parseTemplate(content,"LowercaseEntityName",
                    CommonUtils.firstCharToLowerCase(generatorCodeVO.getClassName()));
            if (isMapper){
                content=CommonUtils.parseTemplate(content,"TableName",generatorCodeVO.getTableName());
                content=CommonUtils.parseTemplate(content,"FieldIfList",getFieldIfList());
                content=CommonUtils.parseTemplate(content,"FieldJoin",getFieldJoin());
                content=CommonUtils.parseTemplate(content,"FieldMapJoin",getFieldMapJoin());
                content=CommonUtils.parseTemplate(content,"FieldMapList",getFieldMapList());
                content=CommonUtils.parseTemplate(content,"FieldSetList",getFieldSetList());
                content=CommonUtils.parseTemplate(content,"PrimaryKeyList",getPrimaryKeyList());
                content=CommonUtils.parseTemplate(content,"StringFieldJoin",getStringFieldJoin());
            }
            String dir="";
            dir = this.baseDir+generatorCodeVO.getModuleName()+dirPath;
            FileUtils.createDir(dir);
            if(type=="Service.java"){

                CommonUtils.writeContentToFile(dir+"I"+generatorCodeVO.getClassName()+type,content );
            }else{

                CommonUtils.writeContentToFile(dir+generatorCodeVO.getClassName()+type,content );
            }
        }catch (Exception e){
            e.printStackTrace();
            content=type+"生成失败:"+e.getMessage();
        }
        return content;
    }


    private String createAL(){
        String filePath="static/temp/al.tlp";
        String dirPath="/logic/";
        String type="AL.java";
        String content=this.getJavaContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createServiceImpl(){
        String filePath="static/temp/serviceImpl.tlp";
        String dirPath="/service/impl/";
        String type="ServiceImpl.java";
        String content=this.getJavaContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createIservice(){
        String filePath="static/temp/iService.tlp";
        String dirPath="/service/";
        String type="Service.java";
        String content=this.getJavaContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createDao(){
        String filePath="static/temp/dao.tlp";
        String dirPath="/dao/";
        String type="Dao.java";
        String content=this.getJavaContent(filePath,this.generatorCodeVO,dirPath,type,false,false);
        return content;
    }
    private String createMapper(){
        String filePath="static/temp/mapper.tlp";
        String dirPath="/dao/";
        String type="Dao.xml";
        String content=this.getJavaContent(filePath,this.generatorCodeVO,dirPath,type,false,true);
        return content;
    }
    private String createVO(){
        String filePath="static/temp/vo.tlp";
        String dirPath="/service/vo/";
        String type="VO.java";
        String content=this.getJavaContent(filePath,this.generatorCodeVO,dirPath,type,true,false);
        return content;
    }
    private String createEntity(){
        String filePath="static/temp/entity.tlp";
        String dirPath="/dao/entity/";
        String type="Entity.java";
        String content=this.getJavaContent(filePath,this.generatorCodeVO,dirPath,type,true,false);
        return content;
    }
    public String getFieldIfList(){
        StringBuffer fieldIfList=new StringBuffer();
        if(columnList !=null){
            Column col = null;
            StringBuffer field = new StringBuffer(Consts.TAB3);
            field.append("<if test=\"" + CommonUtils.firstCharToLowerCase(this.generatorCodeVO.getClassName())+".{0} != null and "
                    +CommonUtils.firstCharToLowerCase(this.generatorCodeVO.getClassName())+".{0} !=''\" >\r\n")
                    .append(Consts.TAB4)
                    .append("and {1} = #{"+CommonUtils.firstCharToLowerCase(this.generatorCodeVO.getClassName())+".{2},jdbcType={3}}\r\n")
                    .append(Consts.TAB2).append("</if>\r\n");
            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                fieldIfList.append(CommonUtils.format(field.toString(),col.getFieldName(),col.getColumnName(),col.getFieldName(),CommonUtils.getDataType(col.getDataType()).toUpperCase()));
            }
        }
        return fieldIfList.toString();
    }
    public String getFieldJoin(){
        StringBuffer fieldJoin = new StringBuffer();
        if(columnList != null){
            Column mdc = null;
            for (int i = 0; i < columnList.size(); i++) {
                mdc=(Column) columnList.get(i);
                fieldJoin.append(mdc.getColumnName()).append(", ");
            }
        }
        return fieldJoin.toString().replaceFirst(", $","");
    }
    public String getFieldMapJoin(){
        StringBuffer fieldMapList= new StringBuffer();
        if(columnList != null){
            Column col = null;
            StringBuffer field = new StringBuffer();
            field.append("#{{0},jdbcType={1}},");
            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                fieldMapList.append(CommonUtils.format(field.toString(),col.getFieldName(),CommonUtils.getDataType(col.getDataType()).toUpperCase()));
            }
        }
        String result=fieldMapList.toString();
        return result.substring(0,result.length()-1);
    }
    public String getFieldMapList(){
        StringBuffer fieldMapList= new StringBuffer();
        if(columnList != null){
            Column col = null;
            StringBuffer field = new StringBuffer(Consts.TAB2);
            field.append("<result property=\"{0}\" column=\"{1}\" javaType=\"java.lang.{2}\" jdbcType=\"{3}\" />\r\n");
            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                fieldMapList.append(CommonUtils.format(field.toString(),col.getFieldName(),col.getColumnName(),col.getFieldType(),CommonUtils.getDataType(col.getDataType()).toUpperCase()));
            }
        }
        return fieldMapList.toString();

    }
    public String getFieldSetList(){
        StringBuffer fieldMapList= new StringBuffer();
        if(columnList != null){

            Column col = null;
            String map="{0} = #{{1},jdbcType={2}},\r\n";
            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                fieldMapList.append(Consts.TAB2).append(CommonUtils.format(map,col.getColumnName(),col.getFieldName(),CommonUtils.getDataType(col.getDataType()).toUpperCase()));
            }
        }
        return fieldMapList.toString().replaceFirst(",$","");

    }
    public String getPrimaryKeyList(){
        StringBuffer keyList= new StringBuffer("");
        if(columnList != null){
            Column col = null;
            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                if(null != col.getColumnKey() && col.getColumnKey().equalsIgnoreCase("pri")){
                    String map=" and {0} = #{{1},jdbcType={2}}";
                    keyList.append(CommonUtils.format(map,col.getColumnName(),col.getFieldName(),CommonUtils.getDataType(col.getDataType()).toUpperCase()));
                }
            }
        }
        String result=keyList.toString();
        return result.substring(5,result.length());
    }
    public String getStringFieldJoin(){
        StringBuffer keyList= new StringBuffer("");
        if(columnList != null){
            Column col = null;
            keyList.append("UPPER(");

            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                if(null != col.getFieldType()){
                    if(col.getFieldType().equals("String")){
                        keyList.append(col.getColumnName());
                        if(i<columnList.size()-1){
                            keyList.append("||'||'||");
                        }
                    }else{
                        keyList.append("to_char("+col.getColumnName()+")");
                        if(i<columnList.size()-1){
                            keyList.append("||'||'||");
                        }
                    }
                }
            }
            keyList.append(")");
        }
        String result=keyList.toString();
        return result.substring(5,result.length());
    }

    public String createAttrList(){
        StringBuffer sb=new StringBuffer();
        if(columnList !=null){
            for (int i=0;i<columnList.size();i++){
                Column column = columnList.get(i);
                sb.append(Consts.TAB1)
                        .append("/** ")
                        .append(CommonUtils.isBlank(column.getFieldDesc())? column.getFieldDesc():column.getFieldName())
                        .append("*/")
                        .append(Consts.ENTER)
                        .append(Consts.TAB1)
                        .append("private "+ column.getFieldType()+" "+column.getFieldName()+";")
                        .append(Consts.ENTER)
                        .append(Consts.ENTER);
            }
        }
        sb.append(Consts.TAB1).append("/**检索字段").append(Consts.ENTER)
                .append(Consts.TAB1).append("private String searchText;")
                .append(Consts.ENTER).append(Consts.ENTER);
        return sb.toString();
    }
    public String createAttrGetsetList(){
        StringBuffer sb=new StringBuffer();
        if(columnList !=null){
            String content = null;
            for(int i=0;i<columnList.size();i++){
                Column column=columnList.get(i);
                if(column !=null){
                    content = CommonUtils.readResourceFile("static/temp/getset.tlp");
                    content = CommonUtils.parseTemplate(content,"EntityName",this.generatorCodeVO.getClassName());
                    content = CommonUtils.parseTemplate(content,"AttrName",CommonUtils.firstCharUpCase(column.getFieldName()));
                    content = CommonUtils.parseTemplate(content,"attrName",CommonUtils.firstCharToLowerCase(column.getFieldName()));
                    content = CommonUtils.parseTemplate(content,"comment",CommonUtils.isBlank(column.getFieldDesc())?column.getColumnName():column.getFieldDesc());
                    content = CommonUtils.parseTemplate(content,"JavaType",column.getFieldType());
                    sb.append(content).append(Consts.ENTER);
                }
            }
        }
        sb.append(Consts.TAB1).append("/**").append(Consts.ENTER)
                .append(Consts.TAB1).append(" * 获取检索字段的值").append(Consts.ENTER)
                .append(Consts.TAB1).append(" * @return String")
                .append(Consts.ENTER).append(Consts.TAB1).append(" */")
                .append(Consts.ENTER).append(Consts.TAB1)
                .append("public String getSearchText(){").append(Consts.ENTER)
                .append(Consts.TAB2).append("return searchText;")
                .append(Consts.ENTER).append(Consts.TAB1).append("}")
                .append(Consts.ENTER);
        sb.append(Consts.TAB1).append("/**").append(Consts.ENTER)
                .append(Consts.TAB1).append(" * 设置检索字段的值").append(Consts.ENTER)
                .append(Consts.TAB1).append(" * @return void")
                .append(Consts.ENTER).append(Consts.TAB1).append(" */")
                .append(Consts.ENTER).append(Consts.TAB1)
                .append("public void setSearchText(String searchText){").append(Consts.ENTER)
                .append(Consts.TAB2).append("this.searchText = searchText;")
                .append(Consts.ENTER).append(Consts.TAB1).append("}")
                .append(Consts.ENTER);
        return sb.toString();

    }
    public String getSearchFieldList(){
        StringBuffer list = new StringBuffer();
        if(columnList != null){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB4);
            field.append("<div class=\"form-group\">\r\n")
                    .append(Consts.TAB5)
                    .append("<label class=\"col-sm-2\">{0}:</label>\r\n")
                    .append(Consts.TAB5)
                    .append("<div class=\"col-sm-4\">\r\n")
                    .append(Consts.TAB6)
                    .append("<input type=\"text\" class=\"form-control\" id=\"{1}Search\" name=\"{1}Search\" placeholder=\"请输入{0}\">\r\n")
                    .append(Consts.TAB5).append("</div>\r\n")
                    .append(Consts.TAB4).append("</div>\r\n");
            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(), StringUtils.isEmpty(col.getFieldDesc())?col.getFieldName():col.getFieldDesc(),col.getFieldName()));
            }
        }
        return list.toString();
    }
    public String getCreateFieldList(){
        StringBuffer list = new StringBuffer();
        if(columnList != null){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB5);
            field.append("<div class=\"form-group\">\r\n")
                    .append(Consts.TAB6)
                    .append("<label class=\"col-sm-2\">{0}:</label>\r\n")
                    .append(Consts.TAB6)
                    .append("<div class=\"col-sm-4\">\r\n")
                    .append(Consts.TAB7)
                    .append("<input type=\"text\" class=\"form-control\" id=\"{1}Create\" name=\"{1}Create\" placeholder=\"请输入{0}\">\r\n")
                    .append(Consts.TAB6).append("</div>\r\n")
                    .append(Consts.TAB5).append("</div>\r\n");
            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(), StringUtils.isEmpty(col.getFieldDesc())?col.getFieldName():col.getFieldDesc(),col.getFieldName()));
            }
        }
        return list.toString();
    }
    public String getEditFieldList(){
        StringBuffer list = new StringBuffer();
        if(columnList != null){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB5);
            field.append("<div class=\"form-group\">\r\n")
                    .append(Consts.TAB6)
                    .append("<label class=\"col-sm-2\">{0}:</label>\r\n")
                    .append(Consts.TAB6)
                    .append("<div class=\"col-sm-4\">\r\n")
                    .append(Consts.TAB7)
                    .append("<input type=\"text\" class=\"form-control\" id=\"{1}Edit\" name=\"{1}Edit\" placeholder=\"请输入{0}\">\r\n")
                    .append(Consts.TAB6).append("</div>\r\n")
                    .append(Consts.TAB5).append("</div>\r\n");
            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(), StringUtils.isEmpty(col.getFieldDesc())?col.getFieldName():col.getFieldDesc(),col.getFieldName()));
            }
        }
        return list.toString();
    }
    public String getShowFieldList(){
        StringBuffer list = new StringBuffer();
        if(columnList != null){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB5);
            field.append("<div class=\"form-group\">\r\n")
                    .append(Consts.TAB6)
                    .append("<label class=\"col-sm-2\">{0}:</label>\r\n")
                    .append(Consts.TAB6)
                    .append("<div class=\"col-sm-4\">\r\n")
                    .append(Consts.TAB7)
                    .append("<input type=\"text\" class=\"form-control\" id=\"{1}Show\" name=\"{1}Show\" placeholder=\"请输入{0}\">\r\n")
                    .append(Consts.TAB6).append("</div>\r\n")
                    .append(Consts.TAB5).append("</div>\r\n");
            for (int i=0;i<columnList.size();i++){
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(), StringUtils.isEmpty(col.getFieldDesc())?col.getFieldName():col.getFieldDesc(),col.getFieldName()));
            }
        }
        return list.toString();
    }
    private String resetList(){
        StringBuffer list =new StringBuffer();
        if(columnList != null){
            Column col = null;
            StringBuffer field = new StringBuffer();
            field.append("$(\"#{0}Search\").val(\"\");\r\n");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(),col.getFieldName()));
            }
        }
        return list.toString();
    }
    private String deleteByPrimaryKeyList(){
        StringBuffer list = new StringBuffer();
        if (columnList !=null){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB3);
            field.append("{LowercaseEntityName}VO.{0}=item.{0};\r\n");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                if(null!=col.getColumnKey() && "pri".equalsIgnoreCase(col.getColumnKey())){
                    list.append(CommonUtils.format(field.toString(),col.getFieldName()));
                }
            }

        }
        return list.toString();
    }

    private String setShowEditValue(){
        StringBuffer list = new StringBuffer();
        if(columnList !=null){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB3);
            field.append("$(\"#{0}Edit\").val(BaseVO.records[0].{0});\r\n");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(),col.getFieldName()));
            }
        }
        return list.toString();
    }

    private String getShowEditList(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer();
            field.append("\"&{0}=\"+item.{0}");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                if(null != col.getColumnKey()&&"pri".equalsIgnoreCase(col.getColumnKey())){
                    list.append(CommonUtils.format(field.toString(),col.getFieldName()));
                }
            }
        }
        String content=null;
        if(null != list && null !=list.toString() && list.toString().trim().startsWith("\"&")){
            content="\"" + list.substring(list.toString().indexOf("&")+1);
        }
        if(null != content && content.trim().endsWith("+")){
            content=content.substring(0,content.lastIndexOf("+"));
        }
        return content;
    }

    private String getPkAssignList(){
        StringBuffer list = new StringBuffer();
        if (null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer("");
            field.append("vo.{0}=item.{0};\r\n");
            field.append(Consts.TAB3);
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                if(null != col.getColumnKey() && "pri".equalsIgnoreCase(col.getColumnKey())){
                    list.append(CommonUtils.format(field.toString(),col.getFieldName()));
                }
            }
        }
        return list.toString();
    }

    public String getPKAssignEdit(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB5);
            field.append("\"&{0}=\" + item.{0} +\r\n");
            StringBuffer firstField = new StringBuffer();
            field.append("\"{0}=\" + item.{0} +\r\n");
            StringBuffer firstFieldOnly = new StringBuffer();
            field.append("\"{0}=\" + item.{0} \r\n");
            StringBuffer lastField = new StringBuffer(Consts.TAB5);
            field.append("\"&{0}=\" + item.{0} \r\n");
            List<Column> pkColumnList=new ArrayList<Column>();
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                if(null != col.getColumnKey() && "pri".equalsIgnoreCase(col.getColumnKey())){
                    pkColumnList.add(col);
                }
            }
            for (int i = 0; i < pkColumnList.size(); i++) {
                col=pkColumnList.get(i);
                if(0==i){
                    if (pkColumnList.size()==1){
                        list.append(CommonUtils.format(firstFieldOnly.toString(),col.getFieldName()));
                    }else{
                        list.append(CommonUtils.format(firstField.toString(),col.getFieldName()));
                    }
                }else if (i==pkColumnList.size()-1){
                    list.append(CommonUtils.format(lastField.toString(),col.getFieldName()));
                }else{
                    list.append(CommonUtils.format(field.toString(),col.getFieldName()));
                }
            }

        }
        return list.toString();
    }
    public String getPkAssignShow(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB5);
            field.append("\"&{0}=\" + item.{0} +\r\n");
            StringBuffer firstField = new StringBuffer();
            firstField.append("\"{0}=\" + item.{0} +\r\n");
            StringBuffer firstFieldOnly = new StringBuffer();
            firstFieldOnly.append("\"{0}=\" + item.{0} \r\n");
            StringBuffer lastField = new StringBuffer(Consts.TAB5);
            lastField.append("\"&{0}=\" + item.{0} \r\n");
            List<Column> pkColumnList=new ArrayList<Column>();
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                if(null != col.getColumnKey() && "pri".equalsIgnoreCase(col.getColumnKey())){
                    pkColumnList.add(col);
                }
            }
            for (int i = 0; i < pkColumnList.size(); i++) {
                col=pkColumnList.get(i);
                if(0==i){
                    if (pkColumnList.size()==1){
                        list.append(CommonUtils.format(firstFieldOnly.toString(),col.getFieldName()));
                    }else{
                        list.append(CommonUtils.format(firstField.toString(),col.getFieldName()));
                    }
                }else if (i==pkColumnList.size()-1){
                    list.append(CommonUtils.format(lastField.toString(),col.getFieldName()));
                }else{
                    list.append(CommonUtils.format(field.toString(),col.getFieldName()));
                }
            }

        }
        return list.toString();
    }
    public String getCreateParaList(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB5);
            field.append("\"&{0}=\" + $(\"#{0}Create\").val() +\r\n");
            StringBuffer firstField = new StringBuffer();
            firstField.append("\"{0}=\" + $(\"#{0}Create\").val() +\r\n");
//            StringBuffer firstFieldOnly = new StringBuffer();
//            field.append("\"{0}=\" + $(\"#{0}Create\").val() +\r\n");
            StringBuffer lastField = new StringBuffer(Consts.TAB5);
            lastField.append("\"&{0}=\" + $(\"#{0}Create\").val()");

            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                if(0==i){
                    list.append(CommonUtils.format(firstField.toString(),col.getFieldName()));

                }else if (i==columnList.size()-1){
                    list.append(CommonUtils.format(lastField.toString(),col.getFieldName()));
                }else{
                    list.append(CommonUtils.format(field.toString(),col.getFieldName()));
                }
            }

        }
        return list.toString();
    }

    public String getEditParaList(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB5);
            field.append("\"&{0}=\" + $(\"#{0}Edit\").val() +\r\n");
            StringBuffer firstField = new StringBuffer();
            firstField.append("\"{0}=\" + $(\"#{0}Edit\").val() +\r\n");
//            StringBuffer firstFieldOnly = new StringBuffer();
//            field.append("\"{0}=\" + $(\"#{0}Create\").val() +\r\n");
            StringBuffer lastField = new StringBuffer(Consts.TAB5);
            lastField.append("\"&{0}=\" + $(\"#{0}Edit\").val()");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                if(0==i){
                    list.append(CommonUtils.format(firstField.toString(),col.getFieldName()));

                }else if (i==columnList.size()-1){
                    list.append(CommonUtils.format(lastField.toString(),col.getFieldName()));
                }else{
                    list.append(CommonUtils.format(field.toString(),col.getFieldName()));
                }
            }

        }
        return list.toString();
    }
    public String getSearchParaList(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB5);
            field.append("\"&{0}=\" + $(\"#{0}Search\").val() +\r\n");
            StringBuffer firstField = new StringBuffer();
            firstField.append("\"{0}=\" + $(\"#{0}Search\").val() +\r\n");
//            StringBuffer firstFieldOnly = new StringBuffer();
//            field.append("\"{0}=\" + $(\"#{0}Create\").val() +\r\n");
            StringBuffer lastField = new StringBuffer(Consts.TAB5);
            lastField.append("\"&{0}=\" + $(\"#{0}Search\").val()");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                if(0==i){
                    list.append(CommonUtils.format(firstField.toString(),col.getFieldName()));

                }else if (i==columnList.size()-1){
                    list.append(CommonUtils.format(lastField.toString(),col.getFieldName()));
                }else{
                    list.append(CommonUtils.format(field.toString(),col.getFieldName()));
                }
            }

        }
        return list.toString();
    }

    public String getResetSearchList(){
        StringBuffer list = new StringBuffer();
        if (null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB2);
            field.append("$(\"#{0}Search\").val(\"\");\r\n");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(),col.getFieldName()));
            }
        }
        return list.toString();
    }
    public String getResetCreateList(){
        StringBuffer list = new StringBuffer();
        if (null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB2);
            field.append("$(\"#{0}Create\").val(\"\");\r\n");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(),col.getFieldName()));
            }
        }
        return list.toString();
    }
    public String getCreateSearchList(){
        StringBuffer list = new StringBuffer();
        if (null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB2);
            field.append("$(\"#{0}Search\").val(\"\");\r\n");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(),col.getFieldName()));
            }
        }
        return list.toString();
    }

    public String getAssignEditList(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer(Consts.TAB3);
            field.append("$(\"#{0}Edit\").val(data.{0});\r\n");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(),col.getFieldName()));
            }
        }
        return list.toString();
    }
    public String getAssignShowList(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col=null;
            StringBuffer field = new StringBuffer("");
            field.append("$(\"#{0}Show\").val(data.{0});\r\n");
            field.append(Consts.TAB3);
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(),col.getFieldName()));
            }
        }
        return list.toString();
    }

    public String getFieldJsonList(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col = null;
            StringBuffer field = new StringBuffer("");
            field.append(",{\r\n");
            field.append(Consts.TAB3);
            field.append("field : \"{0}\",\r\n");
            field.append(Consts.TAB3);
            field.append("title:\"{1}\"\r\n");
            field.append(Consts.TAB2);
            field.append("}");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(),col.getFieldName(),col.getFieldDesc()));
            }
        }
        return list.toString();
    }
    public String getShowFieldPartList(){
        StringBuffer list = new StringBuffer();
        if(null != columnList){
            Column col = null;
            StringBuffer field = new StringBuffer(Consts.TAB3);
            field.append(",{\r\n");
            field.append(Consts.TAB4);
            field.append("title:\"{0}\",\r\n");
            field.append(Consts.TAB4);
            field.append("field : \"{1}\",\r\n");
            field.append(Consts.TAB3);
            field.append("},\r\n");
            for (int i = 0; i < columnList.size(); i++) {
                col=columnList.get(i);
                list.append(CommonUtils.format(field.toString(),StringUtils.isEmpty(col.getFieldDesc())?col.getFieldName():col.getFieldDesc(),col.getFieldName()));
            }
        }
        if (null !=list && null != list.toString()){
            return list.toString().substring(0,list.toString().lastIndexOf(","));
        }
        return list.toString();
    }

    public String createAttrToStringList(){
        StringBuffer sb = new StringBuffer();
        if(columnList != null){
            Column column=null;
            String maps = "sb.append(\"; {0}=\" + ({0}) == null ?\"null\" : {0}.toString());\r\n";
            for (int i=0;i<columnList.size();i++){
                column=(Column)columnList.get(i);
                sb.append(Consts.TAB2).append(CommonUtils.format(maps,column.getFieldName()));
            }
        }

        return sb.toString();
    }
}
