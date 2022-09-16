package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.DisplayInfoDao;
import kr.or.connect.booking.dto.DisplayInfo;
import kr.or.connect.booking.service.DisplayInfoService;
@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	
	@Autowired
	DisplayInfoDao ApiDisplayInfoDao;
	
	@Override
	public List<DisplayInfo> getDisplayInfo(int displayInfoId) {
		List<DisplayInfo> mainList = ApiDisplayInfoDao.getDisplayInfo(displayInfoId);
		return mainList;
	}

	@Override
	public List<DisplayInfo> getLocation(int displayInfoId) {
		List<DisplayInfo> mainList = ApiDisplayInfoDao.getLocation(displayInfoId);
		return mainList;
	}

	@Override
	public List<DisplayInfo> getDetailInfo(int displayInfoId) {
		List<DisplayInfo> mainList = ApiDisplayInfoDao.getDetailInfo(displayInfoId);
		return mainList;
	}

}
