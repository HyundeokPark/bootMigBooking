package kr.or.connect.booking.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.booking.dto.DisplayInfo;
import kr.or.connect.booking.dto.ReservationInfo;
import kr.or.connect.booking.dto.ReservationInfoPrice;
import kr.or.connect.booking.service.CategoryService;
import kr.or.connect.booking.service.CommentImgService;
import kr.or.connect.booking.service.CommentService;
import kr.or.connect.booking.service.DisplayInfoImgService;
import kr.or.connect.booking.service.DisplayInfoService;
import kr.or.connect.booking.service.ProductImageService;
import kr.or.connect.booking.service.ProductPriceService;
import kr.or.connect.booking.service.ProductService;
import kr.or.connect.booking.service.PromotionService;
import kr.or.connect.booking.service.ReservationInfoPriceService;
import kr.or.connect.booking.service.ReservationInfoService;


@RestController
public class ApiRsvController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductPriceService productPriceService;

	@Autowired
	DisplayInfoImgService displayInfoImgService;
	
	@Autowired
	DisplayInfoService displayInfoService;
	
	@Autowired
	CommentImgService cmntImgService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	ReservationInfoService reservationInfoService;
	
	@Autowired
	ReservationInfoPriceService	reservationInfoPriceService;


	@GetMapping(path = "/apiMyReservation")
	public HashMap<String,Object> myReservation(@RequestParam(name="email", required=true) String email){
		List<ReservationInfo> reservationList = reservationInfoService.getReservation(email);
		
		HashMap<String,Object> map = new HashMap<>();
		
		for(ReservationInfo rsv : reservationList ) {
			List<DisplayInfo> displayInfoList = displayInfoService.getDisplayInfo(rsv.getDisplay_info_id());
			for(DisplayInfo displayInfo : displayInfoList ) {
					rsv.setDisplayInfo(displayInfo);
			}
		}
		map.put("reservation", reservationList);
		map.put("size", reservationList.size());
		return map;
	}
	
	@PostMapping(path = "/apicompletersv")
	public HashMap<String,Object> completeRsv(@RequestBody ReservationInfo reservationInfo){
		
		int insrtedRsvID = reservationInfoService.insert(reservationInfo);
		for(ReservationInfoPrice rsvPrice : reservationInfo.getReservationInfoPrice() ) {
			rsvPrice.setReservation_info_id(insrtedRsvID);
			int rsvPriceId = reservationInfoPriceService.insert(rsvPrice);
			rsvPrice.setId(rsvPriceId);
		}	
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		
		
		for(ReservationInfo rsv : reservationInfoService.getReservation(reservationInfo.getReservation_email()) ) {
			if(rsv.getId() == insrtedRsvID) {
					rsv.setReservationInfoPrice(reservationInfo.getReservationInfoPrice());
					map.put("reservation", rsv);
			}
		}	
		return map;
	}
	
	@PutMapping(path ="apicancelrsv")
	public HashMap<String, Object> cancelRsv(@RequestParam(name="reservationInfoId", required =true) int reservationInfoId) {
		reservationInfoService.cancelRsv(reservationInfoId);
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		List<ReservationInfo> cncldRsv = reservationInfoService.getCncldReservation(reservationInfoId);
		
		for(ReservationInfo cncld : cncldRsv) {
			cncld.setReservationInfoPrice(reservationInfoPriceService.getPrice(reservationInfoId));
		}
		
		map.put("reservation", cncldRsv);
		return map;
	}
	
	
	
	

	
}
