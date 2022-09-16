package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.DisplayInfo;

@Service
public interface DisplayInfoService {
	public List<DisplayInfo> getDisplayInfo(int displayInfoId);
	public List<DisplayInfo> getLocation(int displayInfoId);
	public List<DisplayInfo> getDetailInfo(int displayInfoId);
}
