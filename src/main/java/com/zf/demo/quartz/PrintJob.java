package com.zf.demo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Create by zengfei
 * Date 2020/6/16 17:18
 */
public class PrintJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("PrintJob start at:" + printTime + ", prints: Hello Job-" + new Random().nextInt(100));
    }
}
