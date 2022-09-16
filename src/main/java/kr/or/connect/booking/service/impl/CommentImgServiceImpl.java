package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.CommentImgDao;
import kr.or.connect.booking.dto.Comment;
import kr.or.connect.booking.dto.CommentImg;
import kr.or.connect.booking.service.CommentImgService;

@Service
public class CommentImgServiceImpl implements CommentImgService {
	@Autowired
	CommentImgDao ApiCommentImgDao;
	
	@Override
	public List<CommentImg> getCommentImg() {
		List<CommentImg> mainList = ApiCommentImgDao.getCommentImg();
		return mainList;
	}

	@Override
	public List<CommentImg> downloadCommentImg(int imgId) {
		List<CommentImg> mainList = ApiCommentImgDao.downloadCommentImg(imgId);
		return mainList;
	}

	@Override
	public int insert(CommentImg cmntImg) {
		
		return ApiCommentImgDao.insert(cmntImg);
	}
}


