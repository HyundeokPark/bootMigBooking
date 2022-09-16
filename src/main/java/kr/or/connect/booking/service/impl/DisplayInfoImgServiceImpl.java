package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.DisplayInfoImgDao;
import kr.or.connect.booking.dto.DisplayInfoImage;
import kr.or.connect.booking.service.DisplayInfoImgService;
@Service
public class DisplayInfoImgServiceImpl implements DisplayInfoImgService {
	
	
	@Autowired
	DisplayInfoImgDao ApiDisplayInfoImg;
	@Override
	public List<DisplayInfoImage> getDisplayImg(int displayInfoId) {
		List<DisplayInfoImage> mainList = ApiDisplayInfoImg.getDisplyImg(displayInfoId);
		return mainList;
		
	}

}
