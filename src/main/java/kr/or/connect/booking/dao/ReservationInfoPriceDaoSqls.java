package kr.or.connect.booking.dao;

public class ReservationInfoPriceDaoSqls {
	public static final String GET_PRICE = 		 "select * "+
												 "from reservation_info_price "+
												 "where reservation_info_id = :reservationInfoId";
												 
												

}
