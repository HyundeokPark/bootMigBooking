package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.Promotion;
@Service
public interface PromotionService {
	public List<Promotion> getPromotions();
	public List<Promotion> getApiPromotions();
	
}
