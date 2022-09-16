package kr.or.connect.booking.dao;

public class DisplayInfoDaoSqls {
	public static final String GET_DIPLAY_INFO = "select a.id as categoryId, a.name as categoryName, "+
												 "b.content as productContent, b.description as productDescription, "+
												 "b.event as productEvent, b.id as productId, "+
												 "c.id as displayInfoId, c.email, c.homepage, c.opening_hours as openingHours, "+
												 "c.place_lot as placeLot, c.place_name as placeName, c.place_street as placeStreet, "+
												 "c.create_date as createDate, c.modify_date as modifyDate, c.tel as telephone "+
												 "from category as a join product as b join display_info as c "+
												 "where c.id = :displayInfoId and c.product_id = b.id and b.category_id = a.id;";


	public static final String GET_DETAIL_INFO = "select "+
												 "b.content as productContent "+
												 "from category as a join product as b join display_info as c "+
												 "where c.id = :displayInfoId and c.product_id = b.id and b.category_id = a.id;";

	public static final String GET_LOCATION = "select "+
											  "c.place_lot as placeLot, c.place_name as placeName, c.place_street as placeStreet, "+
											  "c.tel as telephone "+
											  "from category as a join product as b join display_info as c "+
											  "where c.id = :displayInfoId and c.product_id = b.id and b.category_id = a.id;";

	
}
