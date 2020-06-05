package com.zf.demo.generator.logic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.demo.addressmanage.logic.AddressInfoAL;
import com.zf.demo.generator.dao.GeneratorDao;
import com.zf.demo.generator.dao.entity.TableEntity;
import com.zf.demo.generator.service.vo.Column;
import com.zf.demo.generator.service.vo.GeneratorCodeVO;
import com.zf.demo.generator.service.vo.TableVO;

import com.zf.demo.utils.BaseVO;
import com.zf.demo.utils.Creater;
import com.zf.demo.utils.RedisUtil;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by zengfei
 * Date 2019/12/13 11:46
 */
@Component
public class GeneratorAL {
    @Autowired
    private GeneratorDao generatorDao;
    @Resource
    private RedisUtil redisUtil;

    public List<TableEntity> getTables(){
        List<TableEntity> list=generatorDao.getTables();
        return list;
    }
    public List<TableEntity> getAllColumns(Page<?> page,TableEntity entity){
//        AddressInfoAL aaa1 =new AddressInfoAL();
//        AddressInfoEntity a1=new AddressInfoEntity();
//        a1.setN(10);
//        a1.setM("aaaa");
//        int i=aaa1.insert(a1);
//        System.out.println(i);
//        AddressInfoAL2 aaa =new AddressInfoAL2();
//        AddressInfoEntity a=new AddressInfoEntity();
//        a.setN(10);
//        a.setM("aaaa");
//        int i=aaa.insert(a);
//        System.out.println(i);

//        Day day = new Day();
//        day.setDate("20180101");
//        day.setDaysId("222");
//        boolean b=redisUtil.set("a10",day);
//        for (int i = 0; i < 100; i++) {
//            redisUtil.set("a"+i,"aa"+i);
//        }
//        redisUtil.del("a1");
//        System.out.println(redisUtil.getKeysByScan("a",10L));
//        System.out.println(redisUtil.getValuesByScan("a",10L));
//        redisUtil.scanDel("a",10L);
//        System.out.println("aaaaa");
        List<TableEntity> list=generatorDao.getAllColumns(page,entity);
        return list;
    }
    public BaseVO<GeneratorCodeVO> generatorCode(GeneratorCodeVO generatorCodeVO){
        BaseVO<GeneratorCodeVO> baseVO = new BaseVO<>();
        GeneratorCodeVO codeVO=new GeneratorCodeVO();
        try{
            List<Column> list=new ArrayList<Column>();
            JSONArray jsonArray=JSONArray.fromObject(generatorCodeVO.getColList());
            for(int i = 0 ;i<jsonArray.size();i++){
                JSONObject jsonObject=JSONObject.fromObject((Object)jsonArray.get(i));
                list.add((Column)JSONObject.toBean(jsonObject,Column.class));
            }
            if(null!=list&&list.size()>0){
                Column firstVO=list.get(0);
                codeVO.setProjectName(null==generatorCodeVO.getProjectName()?"":generatorCodeVO.getProjectName());
                codeVO.setTableName(null==firstVO.getTableName()?"":firstVO.getTableName());
                codeVO.setTableCommnet(null==generatorCodeVO.getTableCommnet()?"":generatorCodeVO.getTableCommnet());
                codeVO.setModuleName(null==generatorCodeVO.getModuleName()?"":generatorCodeVO.getModuleName());
                codeVO.setModuleComment(null==generatorCodeVO.getModuleComment()?"":generatorCodeVO.getModuleComment());
                codeVO.setClassName(null==generatorCodeVO.getClassName()?"":generatorCodeVO.getClassName());
                codeVO.setClassComment(null==generatorCodeVO.getClassComment()?"":generatorCodeVO.getClassComment());

            }
            Creater creater=new Creater(codeVO,list);
            creater.createZip();
            baseVO.setStatus("0000");
            baseVO.setSuccess(true);
            baseVO.setMessage("生成成功");
        }catch (Exception e){
            baseVO.setStatus("9999");
            baseVO.setSuccess(false);
            baseVO.setMessage("生成失败");
        }

        return baseVO;

    }
}
