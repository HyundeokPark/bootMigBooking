package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.ReservationInfoDao;
import kr.or.connect.booking.dto.ReservationInfo;
import kr.or.connect.booking.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {
	@Autowired 
	ReservationInfoDao reservationInfoDao;
	@Override
	public int insert(ReservationInfo reservationInfo) {
		
		return reservationInfoDao.insert(reservationInfo);
	}



	@Override
	public List<ReservationInfo> getReservation(String email) {
		
		return reservationInfoDao.getReservation(email);
	}

	@Override
	public int cancelRsv(int reservationInfoId) {
		
		return reservationInfoDao.cancelReservation(reservationInfoId);
	}

	@Override
	public List<ReservationInfo> getCncldReservation(int reservationInfoId) {
	
		return reservationInfoDao.getCncldReservation(reservationInfoId);
	}

}
