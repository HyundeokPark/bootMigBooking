package kr.or.connect.booking.dao;

public class ProductImageDaoSqls {
	public static final String GET_PRDIMG = "select a.type as type, a.product_id as productId, a.id as productImageId, a.file_id as fileInfoId ,"+
											"b.file_name as fileName, b.save_file_name as saveFileName, b.content_type as contentType, "+
											"b.delete_flag as deleteFlag , b.create_date as createDate , b.modify_date as modifyDate, "+
											"c.id as displayId "+
											"from product_image as a join file_info as b join display_info as c "+
											"where c.id = :displayInfoId and a.file_id = b.id and c.product_id = a.product_id;";
}
