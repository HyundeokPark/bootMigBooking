package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.ImgFile;

@Service
public interface ImgFileService {
	public int uploadFile(ImgFile file);

	public List<ImgFile> getImgById(int imgId);
}
