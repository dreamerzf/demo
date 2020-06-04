package com.zf.demo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * Create by zengfei
 * Date 2020/5/19 17:01
 */
public class PathUtil {

    public String getClassPath(Class cla){
        try {
            return java.net.URLDecoder.decode(getClassPathFile(cla).getAbsolutePath(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }


    public File getClassPathFile(Class cla){
        File file = getClassFile(cla);
        int count =cla.getName().split(".").length;
        for (int i=0;i<count;i++){
            file=file.getParentFile();
        }
        if(file.getName().toUpperCase().endsWith(".JAR!")){
            file=file.getParentFile();
        }
        return file;
    }
    public File getClassFile(Class cla){
        URL path = cla.getResource(cla.getName().substring(cla.getName().lastIndexOf(".")+1)+".class");
        if(path == null){
            String name=cla.getName().replaceAll("[.]","/");
            path = cla.getResource("/"+name+".class");

        }
        return new File(path.getFile());
    }
}
