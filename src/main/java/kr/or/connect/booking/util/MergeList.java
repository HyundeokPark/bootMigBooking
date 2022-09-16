package kr.or.connect.booking.util;

import java.util.List;

import kr.or.connect.booking.dto.Comment;
import kr.or.connect.booking.dto.CommentImg;
import kr.or.connect.booking.dto.ReservationInfo;
import kr.or.connect.booking.dto.ReservationInfoPrice;

public class MergeList {
	
	public void mergeComment(List<CommentImg> usrComntImgList,List<Comment> commentList) {
		for(CommentImg img : usrComntImgList ) {
			for(Comment cmnt : commentList ) {
				if(img.getReservationUserCommentId() == cmnt.getCommentId()) {
					cmnt.setCommentImg(img);
				} else if(cmnt.getCommentImg() == null) {
					cmnt.setCommentImg(new CommentImg());
				} else {

				}
			}
		}

	}
}
