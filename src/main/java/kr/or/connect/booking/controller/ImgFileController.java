package kr.or.connect.booking.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.booking.dto.CommentImg;
import kr.or.connect.booking.dto.ImgFile;
import kr.or.connect.booking.service.CommentImgService;
import kr.or.connect.booking.service.ImgFileService;
import kr.or.connect.booking.util.MediaUtils;
import kr.or.connect.booking.util.SessionCheck;

@Controller
public class ImgFileController {

	@Autowired
	CommentImgService cmntImgService;

	@Autowired
	ImgFileService imgFileService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private SessionCheck sessionChecker = new SessionCheck();

	// 해당함수는 평가기준표에 존재해서 만들었습니다. 딱히 쓰이지는 않습니다.
	@GetMapping("/download")
	public void download(@RequestParam int imgId, HttpServletResponse response) {

		List<CommentImg> cmntImgList = cmntImgService.downloadCommentImg(imgId);

		String fileName = "";
		String saveFileName = "";
		String contentType = "";
		int fileLength = 0;

		for (CommentImg img : cmntImgList) {
			fileName = img.getFileName();
			saveFileName = "e:" + File.separator + "tmp" + File.separator + img.getSaveFileName();
			contentType = img.getContentType();
		}
		File file = new File(saveFileName);
		logger.debug("파일의 길이는 {}", file.length());
		fileLength = (int) file.length();

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (FileInputStream fis = new FileInputStream(saveFileName); OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}

	// 해당 함수는 아래의 github을 참고해 구현한 함수입니다.
	// https://github.com/homekeeper89/friend_workout/blob/master/friend_workout/src/main/java/jake/friend/controller/fileCON.java
	@RequestMapping(path = "/displayimgbyId", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFileById(@RequestParam("name") int imgId) throws Exception {
		String fileName = "";

		List<ImgFile> imgList = imgFileService.getImgById(imgId);
		for (ImgFile img : imgList) {
			fileName = img.getSave_file_name();
		}

		ResponseEntity<byte[]> entity = null;
		logger.info("FILE NAME : " + fileName);
		try (InputStream in = new FileInputStream("e:" + File.separator + "tmp" + File.separator + fileName);) { // uploadPath
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			// step: change HttpHeader ContentType
			if (mType != null) {
				// image file(show image)
				headers.setContentType(mType);
			} else {
				// another format file(download file)
				fileName = fileName.substring(fileName.indexOf("_") + 1);// original file Name
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;

	}

}
