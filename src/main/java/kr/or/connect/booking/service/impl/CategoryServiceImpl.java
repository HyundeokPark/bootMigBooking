package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.booking.dao.CategoryDao;
import kr.or.connect.booking.dto.Category;
import kr.or.connect.booking.dto.Product;
import kr.or.connect.booking.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao ApiCategoryDao;
	
	@Override
	public List<Category> getCategory() {
		List<Category> mainList = ApiCategoryDao.selectCategory();
		return mainList;
	}
}


