package kr.or.connect.booking.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView == null) {
			logger.debug("postHandle 실행      {} 가 종료되었습니다.  return view 는 없습니다.", handler.toString());
		}else
		logger.debug("postHandle 실행   {} 가 종료되었습니다.   {}를 view로 사용합니다.    ", handler.toString(), modelAndView.getViewName()); //
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("preHandle 실행   {} 을 호출했습니다.", handler.toString());
		return true;
	}
}
