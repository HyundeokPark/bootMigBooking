package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.ImgFileDao;
import kr.or.connect.booking.dto.ImgFile;
import kr.or.connect.booking.service.ImgFileService;

@Service
public class ImgFileServiceImpl implements ImgFileService {
	@Autowired
	ImgFileDao imgFileDao;

	@Override
	public int uploadFile(ImgFile file) {
		return imgFileDao.insert(file);
	}

	@Override
	public List<ImgFile> getImgById(int imgId) {

		return imgFileDao.getImgById(imgId);
	}

}
