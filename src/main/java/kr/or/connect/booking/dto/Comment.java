package kr.or.connect.booking.dto;

import java.util.ArrayList;
import java.util.List;

public class Comment {
	
	private String reservationDate;
	private String reservationEmail;
	private String reservationName;
	private String reservationTelephone;
	private int score;
	private String createDate;
	private String modifyDate;
	private int productId;
	private int reservationInfoId;
	private int commentId;
	private String comment;
	private CommentImg commentImg;
	
	public CommentImg getCommentImg() {
		return commentImg;
	}
	public void setCommentImg(CommentImg commentImg) {
		this.commentImg = commentImg;
	}
	@Override
	public String toString() {
		return "Comment [reservationDate=" + reservationDate + ", reservationEmail=" + reservationEmail
				+ ", reservationName=" + reservationName + ", reservationTelephone=" + reservationTelephone + ", score="
				+ score + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productId=" + productId
				+ ", reservationInfoId=" + reservationInfoId + ", commentId=" + commentId + ", comment=" + comment
				+ ", commentImg=" + commentImg + "]";
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTelephone() {
		return reservationTelephone;
	}
	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	
	
	
}
