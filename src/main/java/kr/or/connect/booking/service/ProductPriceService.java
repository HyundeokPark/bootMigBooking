package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.ProductPrice;

@Service
public interface ProductPriceService {
	public List<ProductPrice> getPriceInfo(int displayInfoId);
}
