package com.zf.demo.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Create by zengfei
 * Date 2019/12/30 11:27
 */
public class PathUtils {
    /**
     * 取得当前类所在的文件
     * @param clazz
     * @return
     */
    public static File getClassFile(Class clazz){
        URL path=clazz.getResource(clazz.getName().substring(clazz.getName().lastIndexOf(".")+1)+".class");
        if(null==path){
            String name=clazz.getName().replaceAll("[.]","/");
            path=clazz.getResource("/"+name+".class");
        }
        return new File(path.getFile());
    }

    /**
     * 取得当前类的路径
     * @param clazz
     * @return
     */
    public static String getClassFilePath(Class clazz){
        try {
            return URLDecoder.decode(getClassFile(clazz).getAbsolutePath(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 取得当前类所在的classPath目录，比如tomcat下的classes的路径
     * @param clazz
     * @return
     */
    public static File getClassPathFile(Class clazz){
        File file =getClassFile(clazz);
        for (int i=0;i<clazz.getName().split("[.]").length;i++){
            file=file.getParentFile();
        }
        if (file.getName().toUpperCase().endsWith(".JAR!")){
            file=file.getParentFile();
        }
        return file;
    }
    public static String getClassPath(Class clazz){
        try {
            return URLDecoder.decode(getClassPathFile(clazz).getAbsolutePath(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
