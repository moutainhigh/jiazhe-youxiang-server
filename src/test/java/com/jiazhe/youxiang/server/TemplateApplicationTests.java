package com.jiazhe.youxiang.server;

import com.jiazhe.youxiang.TemplateApplication;
import com.jiazhe.youxiang.base.util.RedisUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TemplateApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TemplateApplicationTests {

	@Before
	public void init() {
		System.out.println("开始测试-----------------");
	}

	@After
	public void after() {
		System.out.println("测试结束-----------------");
	}

	@Test
	public void testRedisSet(){
		System.out.println(123);
		RedisUtils.set("name","tujian",5);
	}

}
