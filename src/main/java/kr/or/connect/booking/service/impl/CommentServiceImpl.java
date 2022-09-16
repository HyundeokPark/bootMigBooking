package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.CommentDao;
import kr.or.connect.booking.dto.Comment;
import kr.or.connect.booking.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao CommentDao;
	
	@Override
	public List<Comment> getComment(int displayInfoId) {
		List<Comment> mainList = CommentDao.getComment(displayInfoId);
		return mainList;
	}
	
	@Override
	public List<Comment> getCommentAll(int displayInfoId) {
		List<Comment> mainList = CommentDao.getCommentAll(displayInfoId);
		return mainList;
	}
	
	@Override
	public int getCount(int displayInfoId) {
		
		return CommentDao.getCount( displayInfoId);
	}
	
	@Override
	public int getCountApi() {
		
		return CommentDao.getCountApi();
	}
	
	
	@Override
	public Double getAvg(int displayInfoId) {
		return CommentDao.getAvg(displayInfoId);
	}
	
	
	@Override
	public int getAvgAll() {
		return CommentDao.getAvgAll();
	}

	@Override
	public int insertCmnt(Comment cmnt) {
		
		return CommentDao.insert(cmnt);
	}

	@Override
	public List<Comment> getCommentApi(int cmntId) {
		
		return CommentDao.getCommentApi(cmntId);
	}
}


