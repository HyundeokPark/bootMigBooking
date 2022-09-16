package kr.or.connect.booking.dao;

public class PromotionDaoSqls {
	public static final String SELECT_ALL = "select A.id as id, A.product_id as ProductId, A.file_id as imgId, "
			+ "C.save_file_name as ProductImageUrl " + "from product_image as A join " + "promotion as B join "
			+ "file_info as C " + "where A.product_id = B.product_id AND " + "A.file_id = C.id AND " + "A.type = 'th'";

	public static final String SELECT_API = "select A.id as id, A.product_id as ProductId, A.file_id as imgId, "
			+ "C.save_file_name as ProductImageUrl " + "from product_image as A join " + "promotion as B join "
			+ "file_info as C " + "where A.product_id = B.product_id AND " + "A.file_id = C.id AND " + "A.type = 'th'";

}
