package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.ProductImage;

@Service
public interface ProductImageService {
	public List<ProductImage> getProductImg(int displayInfoId);
}
