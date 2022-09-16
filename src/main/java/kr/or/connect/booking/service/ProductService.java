package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.Product;

@Service
public interface ProductService {
	public static final Integer LIMIT = 4;
	public List<Product> getProductsAll(Integer start);
	public List<Product> getProducts(Integer category, Integer start);
	public int getCount(Integer category);
	public int getTotalCount();
	public int getCountApi();
	
	
}
