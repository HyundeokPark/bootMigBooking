package kr.or.connect.booking.dao;

public class CommentDaoSqls {
	public static final String GET_CMNT_ALL= "select a.comment , a.create_date as createDate, a.modify_date as modifyDate, a.score ,"+
											 "a.product_id as productId, a.id as commentId, "+
											 "b.reservation_name as reservationName, b.reservation_tel as reservationTelephone, "+
											 "b.reservation_email as reservationEmail, b.reservation_date as reservationDate, "+
											 "b.id as reservationInfoId, b.display_info_id as displayId "+
											 "from reservation_user_comment as a join reservation_info as b "+
											 "where  a.product_id = b.product_id and b.id = a.reservation_info_id and b.display_info_id = :displayInfoId;";
	
	public static final String GET_AVG_SCORE_ALL = "select AVG(a.score)" + 
			 								   "from reservation_user_comment as a join reservation_info  as b "+
			 								   "where b.id = a.reservation_info_id and a.product_id = b.product_id ;";
	
	public static final String GET_AVG_SCORE_ = "select AVG(a.score)" + 
			   									"from reservation_user_comment as a join reservation_info  as b "+
			   									"where b.id = a.reservation_info_id and a.product_id = b.product_id and b.display_info_id = :displayInfoId ;";
	
	public static final String GET_AVG_SCORE_TRUNCATE = "select  truncate(ifnull(avg(a.score), 0.0), 1) "+
														"from reservation_user_comment as a join reservation_info as b "+
														"where b.id = a.reservation_info_id and a.product_id = b.product_id and b.display_info_id = :displayInfoId;";
	
	public static final String GET_COUNT_API = "select count(*)" + 
			   							   	   "from reservation_user_comment as a join reservation_info as b "+
			   							   	   "where a.product_id = b.product_id and b.id = a.reservation_info_id ;"; //and b.id = a.reservation_info_id
	
	public static final String GET_COUNT = "select count(*)" + 
			   							   "from reservation_user_comment as a join reservation_info as b "+
			   							   "where a.product_id = b.product_id and b.id = a.reservation_info_id and b.display_info_id = :displayInfoId;";
	
	
	public static final String GET_CMNT = "select a.comment , a.create_date as createDate, a.modify_date as modifyDate, a.score ,"+
			 							  "a.product_id as productId, a.id as commentId, "+
			 							  "b.reservation_name as reservationName, b.reservation_tel as reservationTelephone, "+
			 							  "b.reservation_email as reservationEmail, b.reservation_date as reservationDate, "+
			 							  "b.id as reservationInfoId, b.display_info_id as displayId "+
			 							  "from reservation_user_comment as a join reservation_info as b "+
			 							  "where b.display_info_id = :displayInfoId and  b.id = a.reservation_info_id and a.product_id = b.product_id "+
			 							  "limit 0, 3";
	
	public static final String GET_CMNT_API= "select a.comment , a.create_date as createDate, a.modify_date as modifyDate, a.score ,"+
											 "a.product_id as productId, a.id as commentId, "+
											 "b.reservation_name as reservationName, b.reservation_tel as reservationTelephone, "+
											 "b.reservation_email as reservationEmail, b.reservation_date as reservationDate, "+
											 "b.id as reservationInfoId, b.display_info_id as displayId "+
											 "from reservation_user_comment as a join reservation_info as b "+
											 "where a.id= :cmntId and a.reservation_info_id = b.id";

	
	
	
}
