package kr.or.connect.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.booking.dto.ReservationInfo;
@Service
public interface ReservationInfoService {
	public int insert(ReservationInfo reservationInfo);
	public List<ReservationInfo> getReservation(String email);
	public int cancelRsv(int reservationInfoId);
	public List<ReservationInfo> getCncldReservation(int reservationInfoId);
}
