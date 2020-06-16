package com.zf.demo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;


/**
 * Create by zengfei
 * Date 2020/6/16 17:20
 */
public class MySchedule {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //创建调度器
        SchedulerFactory sf=new StdSchedulerFactory();
        Scheduler scheduler=sf.getScheduler();

        //创建jobDetail实例 并于job绑定
        JobDetail jobDetail=JobBuilder.newJob(PrintJob.class).withIdentity("job1","group1").build();
        // 3、构建Trigger实例,每隔1s执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)//每隔1s执行一次
                        .repeatForever()).build();//一直执行

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------调度开始  ------------");
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------调度结束 ! ------------");

    }
}
