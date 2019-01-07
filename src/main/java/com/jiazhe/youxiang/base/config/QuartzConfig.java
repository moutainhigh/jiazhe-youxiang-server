package com.jiazhe.youxiang.base.config;

import com.jiazhe.youxiang.server.quartz.OrderQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TU
 * @description
 * @date 2019/1/7.
 */
@Configuration
public class QuartzConfig {

    /**
     * 跑订单自动取消(30分钟未取消的订单）任务周期，2小时
     */
    private static final int ORDER_QUARTZ = 2 * 60 * 60;

//    @Bean
//    public JobDetail orderQuartzDetail(){
//        return JobBuilder.newJob(OrderQuartz.class).withIdentity("orderQuartz").storeDurably().build();
//    }
//
//    @Bean
//    public Trigger orderQuartzTrigger(){
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(ORDER_QUARTZ)
//                .repeatForever();
//        return TriggerBuilder.newTrigger().forJob(orderQuartzDetail())
//                .withIdentity("orderQuartz")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
}
