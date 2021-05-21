package com.example.demo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/zipcode/*")
public class ZipcodeController {
	Logger logger = LogManager.getLogger(ZipcodeController.class);
	
	@Autowired(required=true)
	private ZipCodeLogic zipCodeLogic = null;
	
	@RequestMapping("getZipCodeList")
	public String getZipCodeList(HttpServletRequest req) {
		logger.info("getZipCodeList called");
		List<Map<String,Object>> zipList = null;
		zipList = zipCodeLogic.getZipCodeList();
		req.setAttribute("zipList", zipList);
		//return "forward:getZipCodeList.jsp";
		//WEB-INF/jsp/zipcode/getZipCodeList.jsp.jsp
		return "zipcode/getZipCodeList";
	}
	
	@RequestMapping("getZipCodeList2")
	public ModelAndView getZipCodeList2() {
		logger.info("getZipCodeList called");
		List<Map<String,Object>> zipList = null;
		zipList = zipCodeLogic.getZipCodeList();
		ModelAndView mav = new ModelAndView(); 
		mav.setViewName("zipcode/getZipCodeList2");
		mav.addObject("zipList", zipList);
		
		//return "forward:getZipCodeList.jsp";
		//WEB-INF/jsp/zipcode/getZipCodeList.jsp.jsp
		return mav;
	}
}
