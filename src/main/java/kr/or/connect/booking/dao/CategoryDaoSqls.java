package kr.or.connect.booking.dao;

public class CategoryDaoSqls {
	public static final String SELECT_CATEGORY = "select b.id, b.name, count(*) as count "
											   + "from category as b join product as a "
											   + "where a.category_id = b.id group by a.category_id";
		
}
