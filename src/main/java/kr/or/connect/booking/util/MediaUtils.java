package kr.or.connect.booking.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;


// 해당 함수는 아래의 주소를 참고해 구현한 함수입니다.
//https://github.com/homekeeper89/friend_workout/blob/master/friend_workout/src/main/java/jake/friend/util/MediaUtils.java
public class MediaUtils {
	
	private static Map<String, MediaType> mediaMap;
	
	static {
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("JPEG", MediaType.IMAGE_JPEG);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}
	
	

}