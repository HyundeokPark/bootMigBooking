package kr.or.connect.booking.dto;

import java.util.Arrays;
import java.util.List;

public class ReservationInfo {
	
	private int id;
	private int product_id;
	private int display_info_id;
	private String reservation_name;
	private String reservation_tel;
	private String reservation_email;
	private String reservation_date;
	private int cancel_flag;
	private String create_date;
	private String modify_date;
	private int totalPrice;
	private DisplayInfo displayInfo;
	private List<ReservationInfoPrice> reservationInfoPrice;
	


	

	public List<ReservationInfoPrice> getReservationInfoPrice() {
		return reservationInfoPrice;
	}




	public void setReservationInfoPrice(List<ReservationInfoPrice> reservationInfoPrice) {
		this.reservationInfoPrice = reservationInfoPrice;
	}




	@Override
	public String toString() {
		return "ReservationInfo [id=" + id + ", product_id=" + product_id + ", display_info_id=" + display_info_id
				+ ", reservation_name=" + reservation_name + ", reservation_tel=" + reservation_tel
				+ ", reservation_email=" + reservation_email + ", reservation_date=" + reservation_date
				+ ", cancel_flag=" + cancel_flag + ", create_date=" + create_date + ", modify_date=" + modify_date
				+ ", totalPrice=" + totalPrice + ", displayInfo=" + displayInfo + ", reservationInfoPrice="
				+ reservationInfoPrice + "]";
	}
	
	
	

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getDisplay_info_id() {
		return display_info_id;
	}
	public void setDisplay_info_id(int display_info_id) {
		this.display_info_id = display_info_id;
	}
	public String getReservation_name() {
		return reservation_name;
	}
	public void setReservation_name(String reservation_name) {
		this.reservation_name = reservation_name;
	}
	public String getReservation_tel() {
		return reservation_tel;
	}
	public void setReservation_tel(String reservation_tel) {
		this.reservation_tel = reservation_tel;
	}
	public String getReservation_email() {
		return reservation_email;
	}
	public void setReservation_email(String reservation_email) {
		this.reservation_email = reservation_email;
	}
	public String getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}
	public int getCancel_flag() {
		return cancel_flag;
	}
	public void setCancel_flag(int cancel_flag) {
		this.cancel_flag = cancel_flag;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	
	
	
	
	
}
