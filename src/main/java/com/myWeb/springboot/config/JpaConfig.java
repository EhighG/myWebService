package com.myWeb.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // @WebMvcTest는 일반적인 @Configuration은 스캔 X
@EnableJpaAuditing
public class JpaConfig {
}
