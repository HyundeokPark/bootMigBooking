package kr.or.connect.booking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.booking.argumentresolver.HeaderInfo;
import kr.or.connect.booking.dto.Product;
import kr.or.connect.booking.dto.Promotion;
import kr.or.connect.booking.service.ProductService;
import kr.or.connect.booking.service.PromotionService;
import kr.or.connect.booking.util.SessionCheck;

@Controller
public class BookingController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private SessionCheck sessionChecker = new SessionCheck();
	@Autowired
	PromotionService promotionSevrice;
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping(path = "/main")
	public String getList(ModelMap model ,HttpSession session, HeaderInfo headerInfo) {	
		//세션 확인 및 생성
		if(session.getAttribute("isLogin")==null) {
			session.setMaxInactiveInterval(-1);
			session.setAttribute("email", "예약 확인");
			logger.debug("session,isNew = :{}",session.isNew());
		}
		session.setMaxInactiveInterval(-1);
//		sessionChecker.generateSession(session);
		
		
		
		
		logger.debug("{} User_agent입니다.", headerInfo.get("user-agent"));
		
		//	 지정된 프로모션의 '주소' 구하기
		List<Promotion> list = promotionSevrice.getPromotions();
		List<Product> mainList = productService.getProductsAll(0);
		int count = productService.getTotalCount();
		
		
		model.addAttribute("mainList", mainList);
		model.addAttribute("totalcount", count);
		model.addAttribute("list", list);
		model.addAttribute("rsv_email", session.getAttribute("email"));
		return "main";
	}
	
	
	@PostMapping(path="booking/tab")
	public @ResponseBody HashMap<String,Object> getProductList(@RequestParam(name="start", required=false, defaultValue="0") int start, @RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId){
		List<Product> mainList = new ArrayList();
		HashMap<String, Object> map = new HashMap<>();
		int count;
		
		if(categoryId==0) {	
			mainList = productService.getProductsAll(start);
			count = productService.getTotalCount();
		} else {
			mainList = productService.getProducts(categoryId,start);
			count = productService.getCount(categoryId);
		}
		
		map.put("list", mainList);
		map.put("count", count);
		return map;
	}
	
}
