package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.ReservationInfoPrice;
@Service
public interface ReservationInfoPriceService {
	public int insert(ReservationInfoPrice reservationInfoPrice);
	public int insertApi(ReservationInfoPrice reservationInfoPrice);
	public List<ReservationInfoPrice> getPrice(int reservationInfoId);
}
