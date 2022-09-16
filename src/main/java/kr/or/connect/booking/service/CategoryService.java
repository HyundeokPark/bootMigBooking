package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.Category;

@Service
public interface CategoryService {
	public List<Category> getCategory();
}
