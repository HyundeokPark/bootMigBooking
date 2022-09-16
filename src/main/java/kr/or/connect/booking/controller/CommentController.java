package kr.or.connect.booking.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.booking.dto.Comment;
import kr.or.connect.booking.dto.CommentImg;
import kr.or.connect.booking.dto.ImgFile;
import kr.or.connect.booking.service.CommentImgService;
import kr.or.connect.booking.service.CommentService;
import kr.or.connect.booking.service.ImgFileService;
import kr.or.connect.booking.service.ReservationInfoService;
import kr.or.connect.booking.util.MediaUtils;
import kr.or.connect.booking.util.SessionCheck;

@Controller
public class CommentController {
	@Autowired
	CommentImgService cmntImgService;

	@Autowired
	CommentService cmntService;

	@Autowired
	ImgFileService imgFileService;

	@Autowired
	ReservationInfoService rsvInfoService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private SessionCheck sessionChecker = new SessionCheck();

	@RequestMapping(path = "reviewWrite")
	public String goRegistComment(HttpSession session, ModelMap model,
			@RequestParam(name = "reservationInfoId", required = false, defaultValue = "0") int reservationInfoId,
			@RequestParam(name = "productId", required = true) int productId,
			@RequestParam(name = "reservationTitle", required = false) String title) {
//		 화면 보내주기... Spring MVC는 a href도 할려면 이렇게 해야됨... 

		model.put("title", title);
		model.put("reservationInfoId", reservationInfoId);
		model.put("productId", productId);
		logger.debug("제목 : {}              예약ID : {}       상품ID : {}", title, reservationInfoId, productId);
		return "reviewWrite";
	}

	@PostMapping("/comment")
	public String comment(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmmss.SSSSSS");
		Calendar time = Calendar.getInstance();
		String formatedTime = format.format(time.getTime());

		String content = request.getParameter("reviewContent");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int reservationInfoId = Integer.parseInt(request.getParameter("reservationInfoId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		String fileName = file.getOriginalFilename();
		String saveFileName = "img" + File.separator + formatedTime + file.getOriginalFilename();
		String contentType = file.getContentType();
		String imgType = fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType mType = MediaUtils.getMediaType(imgType);

		int contentLength = content.length();

		if (mType == null) {// 이미지 타입이 불일치 시 메인으로 리다이렉트
			return "redirect:main";
		}

		if (contentLength > 400 || contentLength < 5) { // 400초과 혹은 5글자보다 작으면 메인으로 리다이렉트
			return "redirect:main";
		}

		// file유무에 따른 if문에서 쓰일 번수들 미리 선언
		int cmntId = 0;
		int fileId = 0;
		int cmmtImgId = 0;

		logger.debug("업로드 시간 {}", formatedTime);
		logger.debug("리뷰내용 : {}", content);
		logger.debug("rating : {}", rating);
		logger.debug("파일 크기 : {}", file.getSize());
		logger.debug("파일 이름 : {}", fileName);
		logger.debug("파일 저장이름 : {}", saveFileName);
		if (file.getSize() != 0) {
			try (
					// 맥일 경우
					// FileOutputStream fos = new FileOutputStream("/tmp/" +
					// file.getOriginalFilename());
					// 윈도우일 경우
					FileOutputStream fos = new FileOutputStream(
							"e:" + File.separator + "tmp" + File.separator + saveFileName);
					InputStream is = file.getInputStream();) {
				int readCount = 0;
				byte[] buffer = new byte[1024];
				while ((readCount = is.read(buffer)) != -1) {
					fos.write(buffer, 0, readCount);
				}
			} catch (Exception ex) {
				throw new RuntimeException("file Save Error");
			}

			Comment cmnt = new Comment();
			cmnt.setComment(content);
			cmnt.setProductId(productId);
			cmnt.setScore(rating);
			cmnt.setReservationInfoId(reservationInfoId);

			cmntId = cmntService.insertCmnt(cmnt);

			ImgFile imgfile = new ImgFile();
			imgfile.setContent_type(contentType);
			imgfile.setFile_name(fileName);
			imgfile.setSave_file_name(saveFileName);
			imgfile.setDelete_flag(0);

			fileId = imgFileService.uploadFile(imgfile);

			CommentImg cmntImg = new CommentImg();
			cmntImg.setFileId(fileId);
			cmntImg.setReservationInfoId(reservationInfoId);
			cmntImg.setReservationUserCommentId(cmntId);

			cmntImgService.insert(cmntImg);

		}

		if (file.getSize() == 0) {
			Comment cmnt = new Comment();
			cmnt.setComment(content);
			cmnt.setProductId(productId);
			cmnt.setScore(rating);
			cmnt.setReservationInfoId(reservationInfoId);

			cmntId = cmntService.insertCmnt(cmnt);
		}

		return "redirect:myreservation";
	}
}
