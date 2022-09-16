package kr.or.connect.booking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.booking.dto.Category;
import kr.or.connect.booking.dto.Comment;
import kr.or.connect.booking.dto.CommentImg;
import kr.or.connect.booking.dto.DisplayInfo;
import kr.or.connect.booking.dto.DisplayInfoImage;
import kr.or.connect.booking.dto.Product;
import kr.or.connect.booking.dto.ProductImage;
import kr.or.connect.booking.dto.ProductPrice;
import kr.or.connect.booking.dto.Promotion;
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
import kr.or.connect.booking.util.MergeList;


@RestController
public class ApiDetailController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;
	
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
	

	
	

	@GetMapping(path="/apidetailedinfo")
	public HashMap<String,Object> deTailedInfo(@RequestParam(name="displayInfoId", required=true) int displayInfoId){

		List<ProductPrice> priceList = productPriceService.getPriceInfo(displayInfoId);
		List<ProductImage> prdImgList = productImgService.getProductImg(displayInfoId);
		List<DisplayInfoImage> displayImgList =displayInfoImgService.getDisplayImg(displayInfoId);
		List<DisplayInfo> displayInfoList =displayInfoService.getDisplayInfo(displayInfoId);	
		List<Comment> commentList =commentService.getCommentAll(displayInfoId);
		List<CommentImg> commentImgList =cmntImgService.getCommentImg();
		int count = commentService.getCountApi();
		int avg = commentService.getAvgAll();
		
		MergeList ml = new MergeList();
		ml.mergeComment(commentImgList, commentList);


		HashMap<String, Object> map = new HashMap<>();
		map.put("size",commentList.size());
		map.put("ProductPrices", priceList);
		map.put("ProductImgaes", prdImgList);
		map.put("displayInfoImg", displayImgList);
		map.put("displayInfo", displayInfoList);
		map.put("comments", commentList);
		map.put("count", count);
		map.put("avg", avg);

		return map;
	}
	
	

	
}
