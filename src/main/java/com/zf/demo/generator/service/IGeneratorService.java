package com.zf.demo.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.demo.generator.service.vo.GeneratorCodeVO;
import com.zf.demo.generator.service.vo.TableVO;
import com.zf.demo.utils.BaseVO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Create by zengfei
 * Date 2019/12/9 17:31
 */
public interface IGeneratorService {

    ModelAndView page();

    public List<Object> findAllTable();

    public IPage<TableVO> getAllColumns(Page<TableVO> page,TableVO tableVO);

    public BaseVO<GeneratorCodeVO> generatorCode(GeneratorCodeVO generatorCodeVO);

    public void download(String zipName,HttpServletRequest request,HttpServletResponse response);

    public String testPost(HttpServletRequest request, HttpServletResponse response);

}
