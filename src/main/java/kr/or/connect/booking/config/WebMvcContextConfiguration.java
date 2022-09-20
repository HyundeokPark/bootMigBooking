package kr.or.connect.booking.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import kr.or.connect.booking.argumentresolver.HeaderMapArgumentResolver;
import kr.or.connect.booking.interceptor.LogInterceptor;

//@Configuration // 이파일이 설정 파일임을 알려주는 어노테이션
//@EnableWebMvc // 기본적인 맵핑을 자동으로 해줌
//@ComponentScan(basePackages = { "kr.or.connect.booking.controller" }) // controller에서 설정요소들을 찾아라!
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

	// handler에 들어오는 요청값들은 모두 location으로 가서 찾아라!
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
////		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/").setCachePeriod(31556926);
////		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/img/").setCachePeriod(31556926);
//
////		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/").setCachePeriod(31556926);
//		registry.addResourceHandler("/jsp/**").addResourceLocations("/WEB-INF/views/").setCachePeriod(31556926);
////		registry.addResourceHandler("/html/**").addResourceLocations("/WEB-INF/htmls/").setCachePeriod(31556926);
////		registry.addResourceHandler("/font/**").addResourceLocations("/WEB-INF/font/").setCachePeriod(31556926);
//	}

	// default servlet handler를 사용하게 합니다. 맵핑정보가 없는 url이 들어오면, was가 스테틱한 자원을 읽어서 보여줌
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable();
//	}

//	@Override // 컨트롤러 작성 안하고 맵핑해주는 부분, 프로젝트 시작시 기본적으로 index.jsp가 실행된다
//	public void addViewControllers(final ViewControllerRegistry registry) {
//		System.out.println("addViewControllers가 호출됩니다. ");
//		registry.addViewController("/").setViewName("index");
//	}
//
//	@Bean // 이녀석이 뷰가 되는거다!
//	public InternalResourceViewResolver getInternalResourceViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}
//
//	// 인터셉터 추가
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LogInterceptor());
//	}
//
//	// 아규먼트 리졸버 등록
//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//		System.out.println("아규먼트 리졸버 등록");
//		argumentResolvers.add(new HeaderMapArgumentResolver());
//	}
//
//	// 파일업로드 리졸버 등록
//	@Bean
//	public MultipartResolver multipartResolver() {
//		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
//		multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10 최대10mb
//		return multipartResolver;
//	}
}
