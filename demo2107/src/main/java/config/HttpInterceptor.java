package config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/*
 * preHandle() : 요청 처리를 시작한 시각을 재서 요청 속성에 보관함.
 * DispatcherServlet은 preHandle() 메소드가 반드시 true를 반환해야 요청 처리를 계속하며
 * 그 외에는 이 메소드 선에서 요청 처리가 끝났다고 보고 유저에게 곧장 응답객체를 반환한다.
 * 
 * postHandle() : 요청 속성에 보관된 시작 시간을 읽어들여 현재 시각과 비교해서 계산된 소요시간을
 * 모델에 추가한 뒤 뷰에 넘김
 * 
 * afterCompletion() : 요청 처리가 모두 끝난(즉 뷰렌더링까지 완료된) 이후 호출된다.
 * 
 * */

@Component
public class HttpInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) {
		logger.info("================ Before Method");
		long startTime = System.currentTimeMillis();
		logger.info("================ startTime "+startTime);
		request.setAttribute("startTime", startTime);
		return true;
	}
	
	@Override
	public void postHandle( HttpServletRequest request,
							HttpServletResponse response,
							Object handler,
							ModelAndView modelAndView) {
		logger.info("================ Method Executed");
		long startTime = (Long)request.getAttribute("startTime");
		request.removeAttribute("startTime");
		long endTime = System.currentTimeMillis();
		logger.info("================ 소요시간 ====> "+(endTime - startTime));
		modelAndView.addObject("handleTime", endTime - startTime);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, 
								Object handler, 
								Exception ex) {
		logger.info("================ Method Completed");
	}
}
