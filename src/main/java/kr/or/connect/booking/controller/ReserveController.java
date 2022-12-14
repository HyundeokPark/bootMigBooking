package kr.or.connect.booking.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.booking.dto.DisplayInfo;
import kr.or.connect.booking.dto.DisplayInfoImage;
import kr.or.connect.booking.dto.ProductImage;
import kr.or.connect.booking.dto.ProductPrice;
import kr.or.connect.booking.dto.ReservationInfo;
import kr.or.connect.booking.dto.ReservationInfoPrice;
import kr.or.connect.booking.service.CommentImgService;
import kr.or.connect.booking.service.CommentService;
import kr.or.connect.booking.service.DisplayInfoImgService;
import kr.or.connect.booking.service.DisplayInfoService;
import kr.or.connect.booking.service.ProductImageService;
import kr.or.connect.booking.service.ProductPriceService;
import kr.or.connect.booking.service.ReservationInfoPriceService;
import kr.or.connect.booking.service.ReservationInfoService;
import kr.or.connect.booking.util.SessionCheck;

@Controller
public class ReserveController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private SessionCheck sessionChecker = new SessionCheck();

	@Autowired
	ProductPriceService productPriceService;

	@Autowired
	ProductImageService productImgService;

	@Autowired
	DisplayInfoImgService displayInfoImgService;

	@Autowired
	DisplayInfoService displayInfoService;

	@Autowired
	CommentImgService cmntImgService;

	@Autowired
	CommentService commentService;

	@Autowired
	ReservationInfoService reserveInfoService;

	@Autowired
	ReservationInfoPriceService reserveInfoPriceService;

	@RequestMapping(path = "myreservation") // post??? get?????? ????????? ????????????
	public String viewReservation(@RequestParam(name = "resrv_email", required = false) String resrv_email,
			ModelMap model, HttpSession session) {
		String email;
		// ?????? ??????
		if (session.getAttribute("email").equals("?????? ??????") && !resrv_email.equals("")) {
			email = resrv_email;
			session.setAttribute("email", email);
			session.setAttribute("isLogin", true);
		} else {
			email = session.getAttribute("email").toString();
		}

		// ??????????????? ????????? ?????? ???????????? main?????? ???????????????
		if (Pattern.matches("[0-9a-zA-Z]+@[.a-zA-Z]+", email) && Pattern.matches(".{1,50}", email)) {
		} else {
			return "redirect:main";
		}

		List<ReservationInfo> reservationList = reserveInfoService.getReservation(email);

		// ??????????????? ????????? ????????? ????????? ????????? ???????????? ???????????? ?????????. ?????? ???????????? ???????????????
		if (reservationList.size() == 0) {
			session.invalidate();
			return "redirect:main";
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<ReservationInfo> cancelReservation = new ArrayList<ReservationInfo>();
		List<ReservationInfo> usedReservation = new ArrayList<ReservationInfo>();
		List<ReservationInfo> reservation = new ArrayList<ReservationInfo>();

		// displayinfo ??????
		for (ReservationInfo rsv : reservationList) {
			List<DisplayInfo> displayInfoList = displayInfoService.getDisplayInfo(rsv.getDisplay_info_id());
			for (DisplayInfo displayInfo : displayInfoList) {
				rsv.setDisplayInfo(displayInfo);
			}
		}
		// ??????????????? ????????????/??????/???????????? 3????????? ?????? ??????
		for (ReservationInfo rsv : reservationList) {
			String rsvDate = rsv.getReservation_date();
			String today = df.format((new Date()));
			if (rsv.getCancel_flag() == 1) {
				cancelReservation.add(rsv);
			} else if (today.compareTo(rsvDate) < 0) {
				reservation.add(rsv);
			} else {
				usedReservation.add(rsv);
			}
		}

		model.put("reservaionList", reservationList);
		model.put("reservations", reservation);
		model.put("canceledRsvs", cancelReservation);
		model.put("usdRsvs", usedReservation);

		return "myreservation";
	}

	@GetMapping(path = "/reserve")
	public String goReserve(@RequestParam(name = "displayInfoId") int displayInfoId, ModelMap model) {
		// ?????? ????????????
		Random r = new Random();
		int i = r.nextInt(6) - 1;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, i);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String rsvDate = df.format(cal.getTime());

		List<ProductPrice> priceList = productPriceService.getPriceInfo(displayInfoId);
		List<ProductImage> prdImgList = productImgService.getProductImg(displayInfoId);
		List<DisplayInfoImage> displayImgList = displayInfoImgService.getDisplayImg(displayInfoId);
		List<DisplayInfo> displayInfoList = displayInfoService.getDisplayInfo(displayInfoId);

		model.put("productPrices", priceList);
		model.put("productImages", prdImgList);
		model.put("displayInfoImg", displayImgList);
		model.put("displayInfo", displayInfoList);
		model.put("rsvDate", rsvDate);
		return "reserve";
	}

	@PostMapping(path = "reservecomplete")
	public String reserve(HttpServletRequest request, HttpServletResponse response) {

		int displayInfoId = Integer.parseInt(request.getParameter("displayInfoId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String rsvDate = request.getParameter("rsvDate");
		String[] price = request.getParameterValues("price");
		String[] priceCount = request.getParameterValues("count");
		String[] priceId = request.getParameterValues("id");

		// ???????????? ????????? ?????? ???????????? ????????? DB??? ???????????? ?????? main?????? ???????????????
		if (Pattern.matches("[0-9a-zA-Z]+@[a-zA-Z]+[.][a-zA-Z]+", email) // ????????? ??????
				&& Pattern.matches(".{1,50}", email) // email ?????? ????????? 50??? ??????
				&& Pattern.matches("\\d{2,3}-\\d{4}-\\d{4}", tel) // ???????????? ??????
				&& Pattern.matches(".{1,17}", name)) { // ?????? ?????? ????????? 17??? ??????
		} else {
			return "redirect:main";
		}
		ReservationInfo r = new ReservationInfo();
		ReservationInfoPrice rp = new ReservationInfoPrice();

		r.setDisplay_info_id(displayInfoId);
		r.setProduct_id(productId);
		r.setReservation_name(name);
		r.setReservation_email(email);
		r.setReservation_tel(tel);
		r.setCancel_flag(0);
		r.setReservation_date(rsvDate);

		int rsrvId = reserveInfoService.insert(r);

		for (int i = 0; i < price.length; i++) {
			rp.setCount(Integer.parseInt(priceCount[i]));
			rp.setProduct_price_id(Integer.parseInt(priceId[i]));
			rp.setReservation_info_id(rsrvId);
			if (rp.getCount() != 0) {
				reserveInfoPriceService.insert(rp);
			}
		}
		return "redirect:main";
	}

	@PostMapping(path = "cancelRsv")
	public void cancelRsv(@RequestParam(name = "reservationInfoId") int reservationInfoId) {
		reserveInfoService.cancelRsv(reservationInfoId);
	}
}
