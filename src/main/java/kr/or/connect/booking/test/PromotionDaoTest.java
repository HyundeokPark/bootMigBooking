package kr.or.connect.booking.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.booking.config.ApplicationConfig;
import kr.or.connect.booking.dao.ImgFileDao;
import kr.or.connect.booking.dto.ImgFile;

public class PromotionDaoTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ImgFileDao imgFileDao = ac.getBean(ImgFileDao.class);

		List<ImgFile> il = imgFileDao.getImgById(1);

		System.out.println("List----------------------------------------------------------------- : " + il.toString());

	}

}