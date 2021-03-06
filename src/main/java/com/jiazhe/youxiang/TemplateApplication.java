package com.jiazhe.youxiang;

import com.google.common.collect.Sets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Set;

@EnableAsync
@EnableRetry
@EnableCaching
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
public class TemplateApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TemplateApplication.class);
        // 这里可以补充那些加载application.xml之类的操作, 我这里给个例子，我在例子里面直接通过xml去import xml了
        Set<String> sourcesSet = Sets.newHashSet();
        sourcesSet.add("classpath*:applicationContext.xml");
        application.setSources(sourcesSet);
        application.run(args);
    }
}
