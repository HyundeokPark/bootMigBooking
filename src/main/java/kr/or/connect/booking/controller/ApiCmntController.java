package kr.or.connect.booking.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.booking.dto.Comment;
import kr.or.connect.booking.dto.CommentImg;
import kr.or.connect.booking.dto.ImgFile;
import kr.or.connect.booking.service.CommentImgService;
import kr.or.connect.booking.service.CommentService;
import kr.or.connect.booking.service.ImgFileService;

@RestController
public class ApiCmntController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CommentImgService cmntImgService;

	@Autowired
	CommentService cmntService;

	@Autowired
	ImgFileService imgFileService;

	@PostMapping(path = "/apiComment")
	public HashMap<String, Object> registComment(
			@RequestParam(name = "reservationInfoId", required = true) int reservationInfoId,
			@RequestParam(name = "productId", required = true) int productId,
			@RequestParam(name = "score", required = true) int rating,
			@RequestParam(name = "comment", required = true) String content,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		HashMap<String, Object> map = new HashMap<>();
		List<Comment> cmntList = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmmss.SSSSSS");
		Calendar time = Calendar.getInstance();
		String formatedTime = format.format(time.getTime());

//		formatedTime += System.currentTimeMillis(); //파일이름에 마이크로 초 추가 

		logger.debug("생성시간은 {}", formatedTime);

		// file 유무에 따라 if 문에서 쓰이는 변수들 미리 선언
		int cmntId = 0;
		int fileId = 0;
		int cmmtImgId = 0;

		logger.debug("업로드 시간 {}", formatedTime);
		logger.debug("리뷰내용 : {}", content);
		logger.debug("rating : {}", rating);

		if (file != null) {
			String fileName = file.getOriginalFilename();
			String saveFileName = "img" + File.separator + formatedTime + file.getOriginalFilename();
			String contentType = file.getContentType();

			logger.debug("파일 크기 : {}", file.getSize());
			logger.debug("파일 이름 : {}", fileName);
			logger.debug("파일 저장이름 : {}", saveFileName);

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

			cmntList = cmntService.getCommentApi(cmntId);
		}

		if (file == null) {
			Comment cmnt = new Comment();
			cmnt.setComment(content);
			cmnt.setProductId(productId);
			cmnt.setScore(rating);
			cmnt.setReservationInfoId(reservationInfoId);

			cmntId = cmntService.insertCmnt(cmnt);
			logger.debug("커멘트 ID {}", cmntId);
			cmntList = cmntService.getCommentApi(cmntId);
		}

		map.put("comment", cmntList);
		return map;
	}
}
