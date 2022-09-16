package kr.or.connect.booking.dao;

public class ReservationInfoDaoSqls {
	public static final String GET_RESERVATION = "select c.id , c.product_id, "+
												 "c.display_info_id, c.reservation_name, c.reservation_tel, "+
												 "c.reservation_email, c.reservation_date, "+
												 "c.cancel_flag, c.create_date, c.modify_date, "+
												 "sum((a.count*b.price)) as totalPrice "+
												 "from reservation_info_price as a join product_price  as b join reservation_info as c  " +
												 "where c.reservation_email = :email "+
												 "AND c.id = a.reservation_info_id "+
												 "AND a.product_price_id = b.id " + 
												 "group by c.id;";
	
	
	
	public static final String CANCEL_RESERVATION = "update reservation_info "+
													"set cancel_flag = 1 "+
													"where id = :reservationInfoId;";			
			
	
	public static final String GET_CNCLD_RESERVATION = "select c.id , c.product_id, "+
													   "c.display_info_id, c.reservation_name, c.reservation_tel, "+
													   "c.reservation_email, c.reservation_date, "+
													   "c.cancel_flag, c.create_date, c.modify_date, "+
													   "sum((a.count*b.price)) as totalPrice "+
													   "from reservation_info_price as a join product_price  as b join reservation_info as c  " +
													   "where c.id = :reservationInfoId "+
													   "AND c.id = a.reservation_info_id "+
													   "AND a.product_price_id = b.id " + 
													   "group by c.id;";

			
	



}
