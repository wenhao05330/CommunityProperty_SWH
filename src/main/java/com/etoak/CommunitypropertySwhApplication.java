package com.etoak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.etoak.mapper")
public class CommunitypropertySwhApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunitypropertySwhApplication.class, args);
	}

}
