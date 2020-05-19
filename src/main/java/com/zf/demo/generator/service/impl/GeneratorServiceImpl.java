package com.zf.demo.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.demo.generator.dao.entity.TableEntity;
import com.zf.demo.generator.logic.GeneratorAL;
import com.zf.demo.generator.service.IGeneratorService;
import com.zf.demo.generator.service.vo.GeneratorCodeVO;
import com.zf.demo.generator.service.vo.TableVO;
import com.zf.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by zengfei
 * Date 2019/12/9 17:32
 */
@RestController
@RequestMapping(value = "/generator")
public class GeneratorServiceImpl implements IGeneratorService {

    @Autowired
    private GeneratorAL generatorAL;
    @Override
    @RequestMapping(value = "/generatorIndex")
    public ModelAndView page() {
        return new ModelAndView("generator/GeneratorIndex");
    }

    @Override
    @RequestMapping(value = "/getTables")
    public List<Object> findAllTable()
    {
        List<Object> li=new ArrayList<>();
        List<TableEntity> list1=generatorAL.getTables();
        if(!CollectionUtils.isEmpty(list1)){
            for (int i=0;i<list1.size();i++){
                Map<String,Object> map=new HashMap<>();
                map.put("tableName",list1.get(i).getTableName());
                map.put("tableComment",list1.get(i).getTableName()+"("+list1.get(i).getTableComment()+")");
                li.add(map);
            }
        }
        return li;
    }

    @Override
    @RequestMapping(value = "/getAllColumns",method = RequestMethod.POST)
    public IPage<TableVO> getAllColumns(Page<TableVO> page, TableVO tableVO) {
        TableEntity entity=CastEnUtil.convertPojo(tableVO,TableEntity.class);
        List<TableEntity> list=generatorAL.getAllColumns(page,entity);
        if(!CollectionUtils.isEmpty(list)){
            for(TableEntity en:list){
                en.setFieldDesc(en.getColumnComment());
                en.setEntityName(CommonUtils.getEntityName(en.getTableName()));
                en.setFieldName(CommonUtils.getFieldName(en.getColumnName()));
                en.setFieldType(CommonUtils.getFieldType(en.getDataType()));
            }
            page.setRecords(CastEnUtil.convertPojos(list,TableVO.class));
        }
        return page;
    }

    @Override
    @RequestMapping(value = "/generatorCode")
    public BaseVO<GeneratorCodeVO> generatorCode(GeneratorCodeVO generatorCodeVO) {
        return generatorAL.generatorCode(generatorCodeVO);
    }
    @Override
    @RequestMapping(value = "/download")
    public void download(String zipName,HttpServletRequest request,HttpServletResponse response){
        try{
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            BufferedInputStream bis=null;
            BufferedOutputStream bos=null;
            String ctxPath=PathUtils.getClassPath(Creater.class);

            String downloadPath = ctxPath + "/" + zipName + ".zip";
            long fileLength = new File(downloadPath).length();

            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition","attachment;filename=" + new String((zipName + ".zip").getBytes("utf-8"),"ISO8859-1"));
            response.setHeader("Content-Length",String.valueOf(fileLength));
            bis=new BufferedInputStream(new FileInputStream(downloadPath));
            bos=new BufferedOutputStream(response.getOutputStream());
            byte[] buff=new byte[2048];
            int byteRead;
            while (-1!=(byteRead =bis.read(buff,0,buff.length))){
                bos.write(buff,0,byteRead);
            }
            bis.close();
            bos.close();
            FileUtils fu=new FileUtils();
            fu.deleteFile(downloadPath);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    @RequestMapping(value = "/testPost")
    public String testPost(HttpServletRequest request, HttpServletResponse response){
        String a= request.getParameter("userPass");
        String b= request.getParameter("userName");

        return "";
    }
}
