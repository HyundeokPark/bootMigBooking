package kr.or.connect.booking.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.booking.dto.Comment;
import kr.or.connect.booking.dto.CommentImg;
import kr.or.connect.booking.dto.DisplayInfo;
import kr.or.connect.booking.dto.DisplayInfoImage;
import kr.or.connect.booking.dto.ProductImage;
import kr.or.connect.booking.dto.ProductPrice;
import kr.or.connect.booking.service.CommentImgService;
import kr.or.connect.booking.service.CommentService;
import kr.or.connect.booking.service.DisplayInfoImgService;
import kr.or.connect.booking.service.DisplayInfoService;
import kr.or.connect.booking.service.ProductImageService;
import kr.or.connect.booking.service.ProductPriceService;
import kr.or.connect.booking.util.MergeList;
import kr.or.connect.booking.util.SessionCheck;

@Controller
public class DetailController {
	
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
	
	
	@GetMapping(path = "/detail")
	public String goDetail(HttpSession session, ModelMap model , @RequestParam(name="displayInfoId", required=false, defaultValue = "0") int displayInfoId ) {	
//		detail ?????? ????????????... Spring MVC??? a href??? ????????? ????????? ?????????... 
		List<ProductPrice> priceList = productPriceService.getPriceInfo(displayInfoId);
		List<ProductImage> prdImgList = productImgService.getProductImg(displayInfoId);
		List<DisplayInfoImage> displayImgList =displayInfoImgService.getDisplayImg(displayInfoId);
		List<DisplayInfo> displayInfoList =displayInfoService.getDisplayInfo(displayInfoId);	
		List<Comment> commentList =commentService.getComment(displayInfoId);
		List<CommentImg> commentImgList =cmntImgService.getCommentImg();
		double avgScore = commentService.getAvg(displayInfoId);
		int count = commentService.getCount(displayInfoId);
		String email;
		//?????? ?????? ??? ??????
		if(session.getAttribute("isLogin")==null) {
			session.setMaxInactiveInterval(-1);
			session.setAttribute("email", "?????? ??????");
		}		
		
//		sessionChecker.generateSession(session);
		email = session.getAttribute("email").toString();
		
		MergeList ml = new MergeList();
		ml.mergeComment(commentImgList, commentList);
		

		model.put("productPrices", priceList);
		model.put("productImages", prdImgList);
		model.put("displayInfoImg", displayImgList);
		model.put("displayInfo", displayInfoList);
		model.put("comments", commentList);
		model.put("count", count);
		model.put("avgScore",avgScore);
		model.put("email", email);
		return "detail";
	}
	
	
	@GetMapping(path = "/review")
	public String goReview(@RequestParam (name="displayInfoId") int displayInfoId, ModelMap model) {	
//		review ?????? ????????????... Spring MVC??? a href??? ????????? ????????? ?????????... 
//		
		List<Comment> commentList =commentService.getCommentAll(displayInfoId);
		List<CommentImg> commentImgList =cmntImgService.getCommentImg();
		
		int count = commentService.getCount(displayInfoId);
		double avgScore = commentService.getAvg(displayInfoId);
		
		MergeList ml = new MergeList();
		ml.mergeComment(commentImgList, commentList);
	
		model.put("comments", commentList);
		model.put("count", count);
		model.put("avgScore", avgScore);
		return "review";
	}
	
	@PostMapping(path = "detail/btmTab")
	public @ResponseBody HashMap<String,Object> getbtmTab(@RequestParam(name="displayInfoId", required=false, defaultValue="1") int displayInfoId, 
															@RequestParam(name="tabName", required=false, defaultValue=" ???????????? ") String tabName){
		HashMap<String, Object> map = new HashMap<>(); 
		List<DisplayInfo> displayInfoList = null;
		if(tabName.equals(" ???????????? ")) {
			displayInfoList =displayInfoService.getDetailInfo(displayInfoId);
		} else if(tabName.equals(" ???????????? ")){
			displayInfoList =displayInfoService.getLocation(displayInfoId);
		}
		map.put("displayInfo", displayInfoList);
		return map;
	}
	
	@PostMapping(path = "detail/topImg")
	public @ResponseBody HashMap<String,Object> getTopImg(@RequestParam(name="displayInfoId", required=true, defaultValue="1") int displayInfoId){ 
															
		HashMap<String, Object> map = new HashMap<>(); 
		List<ProductImage> prdImgList = productImgService.getProductImg(displayInfoId);
		map.put("productImg", prdImgList);
		return map;
	}
}
