package kr.or.connect.booking.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.booking.dto.Product;
import kr.or.connect.booking.service.ProductImageService;
import kr.or.connect.booking.service.ProductPriceService;
import kr.or.connect.booking.service.ProductService;


@RestController
public class ApiPrdtController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	@Autowired
	ProductPriceService productPriceService;
	
	@Autowired
	ProductImageService productImgService;
	

	@GetMapping(path="/apiproduct")
	public HashMap<String,Object> productList(@RequestParam(name="start", required=false, defaultValue="0") int start, @RequestParam(name="category", required=false, defaultValue="0") int categoryId){
		List<Product> mainList;
		if(categoryId > 5 || categoryId==0) {	
			mainList = productService.getProductsAll(start);
		} else {
			mainList = productService.getProducts(start , categoryId);
		}
		
		int count = productService.getCountApi();
		HashMap<String, Object> map = new HashMap<>();
		map.put("products", mainList);
		map.put("totalcount", count);
		return map;
	}
}
