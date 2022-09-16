package kr.or.connect.booking.dao;

public class DisplayInfoImageDaoSqls {

	
	
	public static final String GET_DISPLAYIMG   = "select a.file_id as fileId, a.display_info_id as displayId, a.id as displayInfoImageId ,"+
												  "b.file_name as fileName, b.save_file_name as saveFileName, b.content_type as contentType, "+
												  "b.delete_flag as deleteFlag , b.create_date as createDate , b.modify_date as modifyDate "+
												  "from display_info_image as a join file_info as b join display_info as c "+
												  "where c.id = :displayInfoId and a.display_info_id = c.id and a.file_id = b.id;";
	
}
