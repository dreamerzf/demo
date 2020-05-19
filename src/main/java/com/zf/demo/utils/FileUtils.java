package com.zf.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Create by zengfei
 * Date 2019/12/30 14:27
 */
public class FileUtils {
    protected static final Logger logger=LoggerFactory.getLogger(FileUtils.class);
    private boolean flag=true;
    private File file;

    /**
     * 创建文件夹，判断目录是否存在，不存在则创建
     * @param dirPath 文件夹完整路径
     * @return
     */
    public static boolean createDir(String dirPath){
        boolean fileExists=false;
        if(CommonUtils.isNotBlank(dirPath)){
            File file=new File(dirPath);
            if(!(fileExists=file.exists())){
                fileExists=file.mkdirs();
            }
        }
        return fileExists;
    }

    /**
     * 创建文件，判断文件是否存在？不存在则创建
     * @param filePath 文件完整路径
     * @return
     * @throws IOException
     */
    public static boolean createFile(String filePath) throws IOException{
        boolean fileExists=false;
        if(CommonUtils.isNotBlank(filePath)){
            File file=new File(filePath);
            if(!(fileExists=file.exists())){
                fileExists=file.createNewFile();
            }
        }
        return fileExists;
    }

    /**
     * 从文件路径获取目录路径
     * @param filePath
     * @return
     */
    public static String getFileDir(String filePath){
        String dir=null;
        if(CommonUtils.isNotBlank(filePath)&&filePath.indexOf("/")!=-1){
            dir=filePath.substring(0,filePath.lastIndexOf("/")+1);
        }
        return dir;
    }

    /**
     * 从文件全名中获取后缀名，并转换成小写
     * @param fileName
     * @return
     */
    public static String getFilePrefix(String fileName){
        String prefix=null;
        if(CommonUtils.isNotBlank(fileName)){
            if(fileName.lastIndexOf(".")!=-1){
                prefix=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
//                prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            }
        }
        return CommonUtils.isNotBlank(prefix)?"":prefix.toLowerCase();
    }

    /**
     * 根据路径删除指定目录或文件，无论存在与否
     * @param sPath 要删除的目录或者文件
     * @return
     */
    public boolean deleteFolder(String sPath){
        flag=false;
        file=new File(sPath);
        if(!file.exists()){
            return flag;
        }else{
            if(file.isFile()){
                return deleteFile(sPath);
            }else {
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     * @param sPath 被删除的文件名
     * @return
     */
    public boolean deleteFile(String sPath){
        flag=false;
        file=new File(sPath);
        if(file.isFile()&&file.exists()){
            file.delete();
            flag=true;
        }
        return flag;
    }

    /**
     * 删除目录文件夹以及目录下的文件
     * @param sPath
     * @return
     */
    public boolean deleteDirectory(String sPath){
        logger.debug("将要删除文件夹--->"+sPath);
//        如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if(!sPath.endsWith(File.separator)){
//            sPath=sPath+File.pathSeparator;
        }
        File dirFile=new File(sPath);
//        如果dir对应的文件不存在，或者不是一个目录，则退出
        if(!dirFile.exists()||!dirFile.isDirectory()){
            return false;
        }
        flag=true;
//        删除文件夹下的所有文件（包括子目录）
        File[] files=dirFile.listFiles();
        for (int i = 0; i < files.length ; i++) {
//            删除子文件
            if(files[i].isFile()){
                flag=deleteFile(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
//            删除子目录
            else{
                flag=deleteDirectory(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
        }
        if (!flag){
            return false;
        }
//        删除当前目录
        if(dirFile.delete()){
            return true;
        }else{
            return false;
        }
    }

}
