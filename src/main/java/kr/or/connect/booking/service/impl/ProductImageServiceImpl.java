package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.ProductImageDao;
import kr.or.connect.booking.dto.ProductImage;
import kr.or.connect.booking.service.ProductImageService;


@Service
public class ProductImageServiceImpl implements ProductImageService {
	
	
	@Autowired
	ProductImageDao ApiProductImageDao;
	
	@Override
	public List<ProductImage> getProductImg(int displayInfoId) {
		List<ProductImage> mainList = ApiProductImageDao.getProductImg(displayInfoId);
		return mainList;
		
	}

}
