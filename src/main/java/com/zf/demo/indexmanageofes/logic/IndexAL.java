package com.zf.demo.indexmanageofes.logic;

import com.zf.demo.indexmanageofes.dao.IndexDao;
import com.zf.demo.indexmanageofes.dao.entity.IndexEntity;
import com.zf.demo.indexmanageofes.service.vo.IndexVO;
import com.zf.demo.utils.BaseVO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Create by zengfei
 * Date 2020/4/28 14:21
 */
@Component
public class IndexAL {
    @Autowired
    private IndexDao indexDao;

    public BaseVO<IndexVO> addIndex(IndexVO vo){
        BaseVO<IndexVO> baseVO = new BaseVO<>();
        HttpURLConnection connection=null;
        URL url=null;
//        DataOutputStream out = null;
        BufferedReader reader=null;
//        String searchResult = "{}";
        try {
            url=new URL("http://localhost:9200/"+vo.getIndexName()+"/");
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
//            connection.setUseCaches(false);
////            connection.setInstanceFollowRedirects(true);
////            connection.setRequestProperty("Accept","*/*");
////            connection.setRequestProperty("Content-Type","application/json");
////            connection.setRequestProperty("Charset","UTF-8");
            connection.connect();
//            out = new DataOutputStream(connection.getOutputStream());
//            out.write(str.getBytes("utf-8"));
//            out.flush();

            reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String lines=null;
            StringBuffer buffer=new StringBuffer();
            while (null!=(lines=reader.readLine())){
                lines=new String(lines.getBytes());
                buffer.append(lines);
            }
            reader.close();
            connection.disconnect();
            JSONObject jsonObject=JSONObject.fromObject(buffer);
            if(jsonObject.getBoolean("acknowledged")){
               baseVO.setSuccess(true);
               baseVO.setStatus("0000");
               baseVO.setMessage("创建成功");
               return baseVO;
            }else{
                baseVO.setMessage("创建失败");
                baseVO.setStatus("9999");
                baseVO.setSuccess(false);
                return baseVO;
            }
        } catch (Exception e) {
            baseVO.setMessage(e.getMessage());
            baseVO.setStatus("9999");
            baseVO.setSuccess(false);
            e.printStackTrace();
            return baseVO;
        }
    }

}
