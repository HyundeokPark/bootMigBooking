package kr.or.connect.booking.util;

import javax.servlet.http.HttpSession;

public class SessionCheck {
	
	public void generateSession(HttpSession session) {
		if(isValid(session)) {
			
		} else {
			session.invalidate();
			session.setMaxInactiveInterval(20);
			session.setAttribute("email", "예약 확인");
		}
	}
	
	
	
	
	public boolean isValid(HttpSession session) {
		if(session.getAttribute("isLogin").equals(true) && !session.getAttribute("email").equals("예매 확인")) {
			return true;
		} else
			return false;
		
	}
	
}
