package com.zf.demo.file.service.impl;

import com.zf.demo.file.service.IFileOperate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Create by zengfei
 * Date 2020/6/1 16:31
 */
@RestController
@RequestMapping(value = "/fileOperate")
public class FileOperateImpl implements IFileOperate {


    @RequestMapping(value = "/upload")
    public void uploadFiles(@RequestParam("files")MultipartFile[] files, HttpServletRequest req, HttpServletResponse res){
        Calendar c=Calendar.getInstance();
        String time=c.getTimeInMillis()+"";
        for (MultipartFile file:files){
            if(!file.isEmpty()){
                String tmpFileDir="D:/fileTest/"+time.substring(10)+"_tmp";
                File file1=new File(tmpFileDir);
                if(!file1.exists()){
                    file1.mkdirs();
                }
                String filePath="D:/fileTest2/"+"/"+time.substring(10);
                try {
                    file.transferTo(new File(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
