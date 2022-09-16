package kr.or.connect.booking.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.connect.booking.util.SessionCheck;

@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private SessionCheck sessionChecker = new SessionCheck();
	
	
	@GetMapping(path = "bookinglogin")
	public String login(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
			
		if(session.getAttribute("isLogin")==null || session.getAttribute("email").equals("예약 확인")) {
			session.setMaxInactiveInterval(-1);
			session.setAttribute("email", "예약 확인");
			return "bookinglogin";
		}
		else {
				RequestDispatcher requestDispatehcer = request.getRequestDispatcher("myreservation");
	            try {
					requestDispatehcer.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
	            return "myreservation";
			}	
			
		  
		
		
		
	}
}
