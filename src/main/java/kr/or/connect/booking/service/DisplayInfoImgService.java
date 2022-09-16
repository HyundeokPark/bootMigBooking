package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.DisplayInfoImage;

@Service
public interface DisplayInfoImgService {
	public List<DisplayInfoImage> getDisplayImg(int displayInfoId);
}
