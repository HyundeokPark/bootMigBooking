package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.CommentImg;

@Service
public interface CommentImgService {
	public List<CommentImg> getCommentImg();
	public List<CommentImg> downloadCommentImg(int imgId);
	public int insert(CommentImg cmntImg);
}
