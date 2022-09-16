package kr.or.connect.booking.dao;

public class CommentImgDaoSqls {
	
	static final String GET_CMNT_IMG = "select "
									 + "a.content_type as contentType, a.id as fileId, a.file_name as fileName, a.save_file_name as  saveFileName, "
									 + "a.delete_flag as deleteFlag, a.create_date as createDate, "
									 + "a.modify_date as modifyDate, "
									 + "b.id as imageId ,b.reservation_user_comment_id as reservationUserCommentId, "
									 + "c.id as reservationInfoId  "
									 + "from "
									 + "file_info as a join reservation_user_comment_image as b join reservation_info as c "
									 + "where "
									 + "c.id = b.reservation_info_id and a.id = b.file_id;";
	
	
	static final String DOWNLOAD_CMNT_IMG = "select "
										   + "a.content_type as contentType, a.id as fileId, a.file_name as fileName, a.save_file_name as saveFileName "
										   + "from "
										   + "file_info as a join reservation_user_comment_image as b "
										   + "where "
										   + "b.id = :imgId and a.id = b.file_id;";

}
