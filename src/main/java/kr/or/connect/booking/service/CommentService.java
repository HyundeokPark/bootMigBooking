package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.Comment;

@Service
public interface CommentService {
	public List<Comment> getComment(int displayInfoId);
	public List<Comment> getCommentAll(int displayInfoId);
	public int getCount(int displayInfoId);
	public int getCountApi();
	public int getAvgAll();
	public Double getAvg(int displayInfoId);
	public int insertCmnt(Comment cmnt);
	public List<Comment> getCommentApi(int cmntId);
}
