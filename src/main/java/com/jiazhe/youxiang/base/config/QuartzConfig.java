package com.jiazhe.youxiang.base.config;

import com.jiazhe.youxiang.server.quartz.BOCCCQuartz;
import com.jiazhe.youxiang.server.quartz.BOCDCQuartz;
import com.jiazhe.youxiang.server.quartz.DJBXQuartz;
import com.jiazhe.youxiang.server.quartz.WeChatAPICacheQuartz;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
//    public JobDetail orderQuartzDetail() {
//        return JobBuilder.newJob(OrderQuartz.class).withIdentity("orderJob").storeDurably().build();
//    }
//
//    @Bean
//    public Trigger orderQuartzTrigger() {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(ORDER_QUARTZ)
//                .repeatForever();
//        return TriggerBuilder.newTrigger().forJob(orderQuartzDetail())
//                .withIdentity("orderTrigger")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }

    @Bean
    @Profile({"djbx-test","djbx-online"})
    public JobDetail DJBXQuartzDetail() {
        return JobBuilder.newJob(DJBXQuartz.class).withIdentity("DJBXJob").storeDurably().build();
    }

    //启动的时候执行一次，然后每间隔2小时执行一次
    @Bean
    @Profile({"djbx-test","djbx-online"})
    public CronTrigger DJBXCronTrigger() throws Exception {
        return TriggerBuilder.newTrigger()
                .forJob(DJBXQuartzDetail())
                .withIdentity("DJBXTrigger")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 */1 * * ?"))
                .build();
    }

    @Bean
    @Profile({"boccc-test","boccc-online"})
    public JobDetail BOCCCQuartzDetail() {
        return JobBuilder.newJob(BOCCCQuartz.class).withIdentity("BOCCCJob").storeDurably().build();
    }

    //每天凌晨1点执行任务
    @Bean
    @Profile({"boccc-test","boccc-online"})
    public CronTrigger BOCCCCronTrigger() throws Exception {
        return TriggerBuilder.newTrigger()
                .forJob(BOCCCQuartzDetail())
                .withIdentity("BOCCCTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 ? * *"))
                .build();
    }

    @Bean
    @Profile({"test","online"})
    public JobDetail BOCDCQuartzDetail() {
        return JobBuilder.newJob(BOCDCQuartz.class).withIdentity("BOCDCJob").storeDurably().build();
    }

    //每月一日上午9点上传储蓄卡对账信息
    @Bean
    @Profile({"test","online"})
    public CronTrigger BOCDCCronTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(BOCDCQuartzDetail())
                .withIdentity("BOCDCTrigger")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ? *"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 9 1 * ? *"))
                .build();
    }

    @Bean
    public JobDetail weChatAPICacheQuartzDetail() {
        return JobBuilder.newJob(WeChatAPICacheQuartz.class).withIdentity("weChatAPICacheJob").storeDurably().build();
    }

    @Bean
    public Trigger weChatAPICacheQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(WECHAT_API_EXPIRES_IN_TIME)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(weChatAPICacheQuartzDetail())
                .withIdentity("weChatAPICacheTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }


}
