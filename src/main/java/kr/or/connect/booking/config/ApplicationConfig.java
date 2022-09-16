package kr.or.connect.booking.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "kr.or.connect.booking.dao", "kr.or.connect.booking.service" })
@Import({ DBConfig.class })
public class ApplicationConfig {

}
