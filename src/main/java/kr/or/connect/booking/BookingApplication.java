package kr.or.connect.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
//@ImportResource(value = {"org.springframework.web.context.support.AnnotationConfigWebApplicationContext"})
public class BookingApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder createSpringApplicationBuilder() {
//        return super.createSpringApplicationBuilder(BookingApplication.class);
//    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BookingApplication.class);
    }

}
