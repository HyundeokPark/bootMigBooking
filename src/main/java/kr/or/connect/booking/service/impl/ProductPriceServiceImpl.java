package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.ProductPriceDao;
import kr.or.connect.booking.dto.ProductPrice;
import kr.or.connect.booking.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
	@Autowired
	ProductPriceDao ApiProductPriceDao;
	

	public List<ProductPrice> getPriceInfo(int displayInfoId) {
		List<ProductPrice> mainList = ApiProductPriceDao.getPriceInfo(displayInfoId);
		return mainList;
	}
}


