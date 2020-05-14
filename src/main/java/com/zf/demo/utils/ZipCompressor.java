package com.zf.demo.utils;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Create by zengfei
 * Date 2019/12/30 14:18
 */
public class ZipCompressor {
    protected static final Logger logger=LoggerFactory.getLogger(ZipCompressor.class);
    static final int BUFFER=8192;
    private File zipFile;

    public ZipCompressor(String pathName) {
        this.zipFile = new File(pathName);
    }
    public void compress(String srcPathName){
        File file=new File(srcPathName);
        if(!file.exists()){
            boolean success=file.mkdir();
            if(logger.isInfoEnabled()){
                logger.info("创建目录"+(success?"成功":"失败")+":"+srcPathName);
            }
        }
        FileOutputStream fileOutputStream= null;
        try {
            fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos=new CheckedOutputStream(fileOutputStream,new CRC32());
            ZipOutputStream out=new ZipOutputStream(cos);
            String basedir="";
            compress(file,out,basedir);//压缩文件或者目录
            out.close();
//            最终删除这个文件夹
            com.zf.demo.utils.FileUtils fu=new com.zf.demo.utils.FileUtils();
            fu.deleteFolder(srcPathName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 压缩文件或者目录
     * @param file
     * @param out
     * @param baseDir
     */
    private void compress(File file,ZipOutputStream out,String baseDir){
//        判断是目录还是文件
        if(file.isDirectory()){
            System.out.println("压缩："+baseDir+file.getName());
            this.compressDirectory(file,out,baseDir);
        }else{
            System.out.println("压缩："+baseDir+file.getName());
            this.compressFile(file,out,baseDir);
        }
    }

    /**
     * 压缩一个目录
     * @param file
     * @param out
     * @param baseDir
     */
    private void compressDirectory(File file,ZipOutputStream out,String baseDir){
        if(!file.exists()) return;
        File[] files=file.listFiles();
        for (int i = 0; i < files.length; i++) {
            compress(files[i],out,baseDir+file.getName()+"/");
        }
    }

    /**
     * 压缩一个文件
     * @param file
     * @param out
     * @param baseDir
     */
    private void compressFile(File file,ZipOutputStream out,String baseDir){
        if(!file.exists()) return ;
        try {
            BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(baseDir+file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count=bis.read(data,0,BUFFER))!=-1){
                out.write(data,0,count);
            }
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
