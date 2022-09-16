package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.ProductDao;
import kr.or.connect.booking.dto.Product;
import kr.or.connect.booking.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;
	public List<Product> getProductsAll(Integer start) {
		List<Product> mainList = productDao.selectAll(start , ProductService.LIMIT);
		return mainList;
		}	
	
	@Override
	public List<Product> getProducts(Integer category, Integer start) {
		List<Product> mainList = productDao.selectProducts(start, ProductService.LIMIT , category);
		return mainList;
	}

	@Override
	public int getCount(Integer category) {
		int count = productDao.getCount(category);
		return count;
	}
	
	@Override
	public int getTotalCount() {
		int count = productDao.getTotalCount();
		return count;
	}

	@Override
	public int getCountApi() {
		int count = productDao.getCountApi();
		return count;
		}
}


