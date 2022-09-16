package kr.or.connect.booking.dao;

public class ImgFileDaoSqls {
	public static final String SELECT_IMG_BY_ID = "select id as id, file_name, save_file_name, "
			+ "content_type, delete_flag, create_date, modify_date " + "from file_info " + "where id =:imgId;";

}
