package com.johnson.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.johnson.hosp.mapper")
public class HospConfig {
}
