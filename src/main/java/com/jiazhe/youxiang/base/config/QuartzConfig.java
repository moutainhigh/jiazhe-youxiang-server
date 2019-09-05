package com.jiazhe.youxiang.base.config;

import com.jiazhe.youxiang.server.quartz.BOCCCQuartz;
import com.jiazhe.youxiang.server.quartz.WeChatAPICacheQuartz;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
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

    /**
     * 微信接口获取结果过期时间(秒）
     */
    private static final Integer WECHAT_API_EXPIRES_IN_TIME = 7200;

    /**
     * 中行定时任务间隔时间 1天
     */
    private static final Integer ONE_DAY = 24 * 60 * 60;

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


    @Bean
    public JobDetail BOCCCQuartzDetail() {
        return JobBuilder.newJob(BOCCCQuartz.class).withIdentity("BOCCCQuartz").storeDurably().build();
    }

    @Bean
    public Trigger BOCCCQuartzTrigger() throws Exception{
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(ONE_DAY)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(BOCCCQuartzDetail())
                .withIdentity("BOCCCQuartz")
                .startNow()
//                .startAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-09-04 01:00:00"))
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail weChatAPICacheQuartzDetail() {
        return JobBuilder.newJob(WeChatAPICacheQuartz.class).withIdentity("weChatAPICacheQuartz").storeDurably().build();
    }

    @Bean
    public Trigger weChatAPICacheQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(WECHAT_API_EXPIRES_IN_TIME)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(weChatAPICacheQuartzDetail())
                .withIdentity("weChatAPICacheQuartz")
                .withSchedule(scheduleBuilder)
                .build();
    }


}
