package kr.or.connect.booking.dao;

public class ProductDaoSqls {
	public static final String SELECT_ALL = "select a.id, a.category_id as c_id, a.description as title, a.content as content, "
			+ "b.place_street as addr, b.id as displayInfoId, " + "d.save_file_name as path, c.file_id as imgId, "
			+ "c.type as type " + "from product as a join display_info as b join product_image as c join file_info as d "
			+ "where a.id = b.product_id AND a.id = c.product_id AND c.file_id = d.id AND c.type = 'th'"
			+ "limit :start, :limit";

	public static final String SELECT_PRODUCTS = "select a.id, a.category_id as c_id, a.description as title, a.content as content, "
			+ "b.place_street as addr, " + "d.save_file_name as path,  b.id as displayInfoId, "
			+ "c.type as type, c.file_id as imgId "
			+ "from product as a join display_info as b join product_image as c join file_info as d "
			+ "where a.category_id = :category_id AND a.id = b.product_id AND a.id = c.product_id AND c.file_id = d.id AND c.type = 'th'"
			+ "limit :start, :limit";

	public static final String GET_COUNT = "select count(*) as count " + "from product as a "
			+ "join display_info as b " + "where a.id = b.product_id " + "AND a.category_id = :category_id";

	public static final String GET_COUNT_API = "select count(*) as count "
			+ "from product as a join display_info as b join product_image as c join file_info as d "
			+ "where a.id = b.product_id AND a.id = c.product_id AND c.file_id = d.id AND c.type = 'th'";

	public static final String GET_TOTALCOUNT = "select count(*) as totalcount " + "from product as a "
			+ "join display_info as b " + "where a.id = b.product_id ";
}
