package com.ats.qzj.atschapter2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ats.qzj.atschapter2.atsService", "com.ats.qzj.atschapter2.mapper"}) // 确保包含 @Service 的包
public class AtsChapter2Application {

	public static void main(String[] args) {
		SpringApplication.run(AtsChapter2Application.class, args);
	}

}
