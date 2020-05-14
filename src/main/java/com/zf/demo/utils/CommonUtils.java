package com.zf.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Create by zengfei
 * Date 2019/12/19 17:04
 */
public final class CommonUtils {
    /**
     * 过滤所有以<开头  以>结尾的标签
     */
    private final static String regxpForHtml="<([^>]*)>";
    /**
     * 数字正则表达式
     */
    private final static Pattern REG_INTEGER = Pattern.compile("(\\+|\\-)?\\d+");
    /**
     * 小写正则表达式
     */
    private final static Pattern IS_LOWCASE = Pattern.compile("[a-z]+");
    /**
     * 大写正则表达式
     */
    private final static Pattern IS_UPPERCASE = Pattern.compile("[A-Z]+");
    /**
     * 十六进制正则表达式
     */
    private final static char[] CHAR_ARRAY = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    /**
     * 手机号表达式
     */
    private final static Pattern MOBILE_PATTERN = Pattern.compile("^(13[0-9]|14[5|7]|15[0-3|5-9]|18[0|2|3|5-9])\\d{8}$");
    /**
     * 邮件正则表达式
     */
    private final static Pattern MAIL_PATTERN = Pattern.compile("^\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b$");
    private CommonUtils(){

    }
    public static String getEntityName(String tableName){
        String entityName="";
        if(tableName !=null &&tableName.length()!=0){
            tableName=tableName.toLowerCase();
            if(tableName.contains("_")){
                String[] strArr=tableName.split("_");
                for (int i=0;i<strArr.length;i++){
                    entityName+=firstCharUpCase(strArr[i]);
                }
            }else {
                entityName=firstCharUpCase(tableName);
            }
        }
        return entityName;
    }
    public static String getFieldName(String columnName){
        String fieldName="";
        if(columnName != null && columnName.length()!=0){
            columnName=columnName.toLowerCase();
            if(columnName.contains("_")){
                String[] strArr=columnName.split("_");
                fieldName=strArr[0];
                for (int i=1;i<strArr.length;i++){
                    fieldName+=firstCharUpCase(strArr[i]);
                }

            }else {
                fieldName=columnName;
            }
        }
        return fieldName;
    }
    public static String getFieldType(String dataType){
        String fieldName="";
        if(dataType.equalsIgnoreCase("varchar")||
                dataType.equalsIgnoreCase("char")||
                dataType.equalsIgnoreCase("varchar2")){
            fieldName="String";
        }else if(dataType.equalsIgnoreCase("int")||
                dataType.equalsIgnoreCase("integer")){
            fieldName="Integer";
        }else if (dataType.equalsIgnoreCase("long")||
                dataType.equalsIgnoreCase("bigint")){
            fieldName="Long";
        }else if (dataType.equalsIgnoreCase("float")){
            fieldName="Float";
        }else if (dataType.equalsIgnoreCase("double")||
                dataType.equalsIgnoreCase("decimal")||
                dataType.equalsIgnoreCase("numeric")||
                dataType.equalsIgnoreCase("number")){
            fieldName="Double";
        }else if (dataType.equalsIgnoreCase("date")){
            fieldName="Date";
        }else if (dataType.equalsIgnoreCase("timestamp")||
                dataType.equalsIgnoreCase("time")){
            fieldName="Timestamp";
        }else if (dataType.equalsIgnoreCase("blob")){
            fieldName="byte[]";
        }
        return fieldName;
    }
    public static String getDataType(String dataType){
        if(dataType.equalsIgnoreCase("varchar")||
                dataType.equalsIgnoreCase("char")||
                dataType.equalsIgnoreCase("varchar2")){
            dataType="VARCHAR";
        }else if(dataType.equalsIgnoreCase("int")||
                dataType.equalsIgnoreCase("integer")){
            dataType="INTEGER";
        }
        return dataType;
    }
    public static String firstCharUpCase(String lowCaseStr){
        String str="";
        if(lowCaseStr != null && lowCaseStr.length()!=0){
            str=lowCaseStr.substring(0,1).toUpperCase()+lowCaseStr.substring(1);
        }else{
            str=lowCaseStr;
        }
        return str;
    }
    public static String firstCharToLowerCase(String str){
        if(!isEmpty(str)){
            str= str.substring(0,1).toLowerCase()+str.substring(1);
        }
        return str;
    }

    /**
     * 判断为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(final Object obj){
        return null==obj;
    }

    /**
     * 判断不为空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(final Object obj){
        return !isEmpty(obj);
    }

    /**
     * 判断字符串不为空并且不为空字符
     * @param str
     * @return
     */
    public static boolean isNotBlank(final String str){
        return isNotEmpty(str) && !"".equals(str.trim());
    }
    /**
     * 判断字符串为空字符
     * @param str
     * @return
     */
    public static boolean isBlank(final String str){
        return isEmpty(str) && "".equals(str.trim());
    }
    public static String readResourceFile(String file){
        String content="";
        try{
            InputStream is=CommonUtils.class.getClassLoader().getResourceAsStream(file);
            byte[] b=null;
            byte[] buf=new byte[1024];
            int num=-1;
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            while((num=is.read(buf,0,buf.length))!=-1){
                baos.write(buf,0,num);
            }
            b=baos.toByteArray();
            content=new String(b,"utf-8");
            baos.flush();
            baos.close();
        }catch (Exception e){

        }
        return content;
    }
    public static String parseTemplate(String content,String key,String value){
        try{
            if(content!=null){
                value=java.util.regex.Matcher.quoteReplacement(value);
                content=content.replaceAll("\\{"+key+"\\}",value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return content;
    }
    public static void writeContentToFile(String file,String content)throws Exception{
        FileOutputStream fos=new FileOutputStream(new File(file));
        fos.write(content.getBytes("UTF-8"));
        fos.flush();
        fos.close();
    }
    public static String format(String resource,String... target){
        if(resource == null){
            throw new IllegalArgumentException("parameter is null");
        }
        if(target !=null && target.length>0){
            for(int i=0;i<target.length;i++){
                resource = resource.replace("{"+i+"}",target[i]);
            }
        }
        return resource;
    }

    /**获取配置文件的属性值
     * @param source
     * @param name
     * @return
     */
    public static String getPrepertyByName(String source,String name){
        String value=null;
        ResourceBundle rb=ResourceBundle.getBundle(source);
        value=rb.getString(name).trim();
        return value;
    }
}