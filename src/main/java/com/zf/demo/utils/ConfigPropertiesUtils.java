package com.zf.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * Create by zengfei
 * Date 2019/12/30 14:03
 */
public class ConfigPropertiesUtils {
    protected static final Logger logger=LoggerFactory.getLogger(ConfigPropertiesUtils.class);
    /**
     * 获取某个配置文件的属性值
     * @param resource
     * @param property
     * @return
     */
    public static String getPropertyByName(String resource,String property){
        String tValue="";
        try{
            ResourceBundle rb=ResourceBundle.getBundle(resource);
            tValue=rb.getString(property).trim();
            if("".equals(tValue)){
                if(logger.isInfoEnabled()){
                    logger.info("未找到配置文件或配置项未设定【"+property+"】参数值");
                }
            }
        }catch (Exception e){
            if(logger.isInfoEnabled()){
                logger.info(e.getMessage());
            }
        }
        return tValue;

    }
}
