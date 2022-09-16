package kr.or.connect.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.booking.dao.ReservationInfoPriceDao;
import kr.or.connect.booking.dto.ReservationInfoPrice;
import kr.or.connect.booking.service.ReservationInfoPriceService;

@Service
public class ReservationInfoPriceServiceImpl implements ReservationInfoPriceService {
	
	@Autowired
	ReservationInfoPriceDao reservationInfoPriceDao;
	@Override
	public int insert(ReservationInfoPrice reservationInfoPrice) {
	
		return reservationInfoPriceDao.insert(reservationInfoPrice);
	}

	@Override
	public int insertApi(ReservationInfoPrice reservationInfoPrice) {
		
		return reservationInfoPriceDao.insert(reservationInfoPrice);
	}

	@Override
	public List<ReservationInfoPrice> getPrice(int reservationInfoId) {
	
		return reservationInfoPriceDao.getPrice(reservationInfoId);
	}

	

}
