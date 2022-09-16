package kr.or.connect.booking.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.booking.config.ApplicationConfig;
import kr.or.connect.booking.dto.Promotion;
import kr.or.connect.booking.service.PromotionService;
public class BookingServiceImplTest {

	public static void main(String[] args) {

		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		PromotionService promotionService = ac.getBean(PromotionService.class);
		
		List<Promotion> pr= promotionService.getPromotions();
		
		System.out.println("pr ------------------" + pr.toString());
		
		
	}

}
