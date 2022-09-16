package kr.or.connect.booking.dao;

public class ProductPriceDaoSqls {
	public static final String GET_PRICE_INFO = "select a.id as prdPriceId, "+
												"a.product_id as productId, "+
												"a.price_type_name as priceTypeName, "+
												"a.price, "+
												"a.discount_rate as discountRate, "+ 
												"a.create_date ,"+
												"a.modify_date "+
												"from product_price as a join display_info as b join product as c "+
												"where b.id = :displayInfoId and c.id = a.product_id and b.product_id = c.id;";
							
}
